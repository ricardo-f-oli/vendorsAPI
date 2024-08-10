import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

import com.example.vendorsAPI.domain.entities.Vendor;
import com.example.vendorsAPI.domain.entities.enums.ContractTypeEnum;
import com.example.vendorsAPI.domain.repository.VendorRepository;
import com.example.vendorsAPI.domain.service.UpdateVendorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class UpdateVendorServiceTest {

    @Mock
    private VendorRepository vendorRepository;

    @InjectMocks
    private UpdateVendorService updateVendorService;

    private Vendor existingVendor;
    private Vendor updatedVendor;

    @BeforeEach
    void setUp() {
        existingVendor = new Vendor();
        existingVendor.setId(1L);
        existingVendor.setName("Old Vendor");
        existingVendor.setDocument("12345678901");
        existingVendor.setContractTypeEnum(ContractTypeEnum.CLT);
        existingVendor.setEmail("old@example.com");

        updatedVendor = new Vendor();
        updatedVendor.setName("Updated Vendor");
        updatedVendor.setDocument("98765432109111");
        updatedVendor.setContractTypeEnum(ContractTypeEnum.PJ);
        updatedVendor.setEmail("updated@example.com");
    }

    @Test
    void testUpdateVendor_Success() {
        when(vendorRepository.findById(anyLong())).thenReturn(Optional.of(existingVendor));
        when(vendorRepository.save(any(Vendor.class))).thenReturn(existingVendor);
        Vendor resultVendor = updateVendorService.updateVendor(1L, updatedVendor);
        assertEquals("Updated Vendor", resultVendor.getName());
        assertEquals("98765432109111", resultVendor.getDocument());
        assertEquals(ContractTypeEnum.PJ, resultVendor.getContractTypeEnum());
        assertEquals("updated@example.com", resultVendor.getEmail());
        verify(vendorRepository).save(existingVendor);
    }

    @Test
    void testUpdateVendor_NotFound() {
        when(vendorRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> updateVendorService.updateVendor(1L, updatedVendor));
        verify(vendorRepository, never()).save(any(Vendor.class));
    }
}