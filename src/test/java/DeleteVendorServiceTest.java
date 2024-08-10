import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

import com.example.vendorsAPI.domain.entities.Vendor;
import com.example.vendorsAPI.domain.entities.enums.ContractTypeEnum;
import com.example.vendorsAPI.domain.repository.VendorRepository;
import com.example.vendorsAPI.domain.service.DeleteVendorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class DeleteVendorServiceTest {

    @Mock
    private VendorRepository vendorRepository;

    @InjectMocks
    private DeleteVendorService deleteVendorService;

    private Vendor vendor;

    @BeforeEach
    void setUp() {
        vendor = new Vendor();
        vendor.setId(1L);
        vendor.setName("Test Vendor");
        vendor.setDocument("12345678901");
        vendor.setContractTypeEnum(ContractTypeEnum.CLT);
        vendor.setRegistration("1-CLT");
        vendor.setEmail("test@example.com");
    }

    @Test
    void testDeleteVendorByRegistration_Success() {
        when(vendorRepository.findByRegistration(anyString())).thenReturn(Optional.of(vendor));
        deleteVendorService.deleteByRegistration("1-CLT");
        verify(vendorRepository).delete(vendor);
    }

    @Test
    void testDeleteVendorByRegistration_NotFound() {
        when(vendorRepository.findByRegistration(anyString())).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> deleteVendorService.deleteByRegistration("1-CLT"));
        verify(vendorRepository, never()).delete(any(Vendor.class));
    }
}
