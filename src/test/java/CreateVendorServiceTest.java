import com.example.vendorsAPI.domain.entities.Vendor;
import com.example.vendorsAPI.domain.entities.enums.ContractTypeEnum;
import com.example.vendorsAPI.domain.repository.VendorRepository;
import com.example.vendorsAPI.domain.service.BranchAPICallerService;
import com.example.vendorsAPI.domain.service.CreateVendorService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CreateVendorServiceTest {

    @Mock
    private VendorRepository vendorRepository;

    @InjectMocks
    private CreateVendorService createVendorService;

    @Mock
    private BranchAPICallerService branchAPICallerService;

    private Vendor vendor;

    @BeforeEach
    void setUp() {
        vendor = new Vendor();
        vendor.setName("Test Vendor");
        vendor.setDocument("12345678901");
        vendor.setContractTypeEnum(ContractTypeEnum.CLT);
        vendor.setEmail("test@example.com");
    }

    @Test
    void testCreateVendor_Success() {
        when(vendorRepository.save(any(Vendor.class))).thenReturn(vendor);
        Vendor savedVendor = createVendorService.createVendor(vendor, "FilialNorte");
        verify(vendorRepository).save(vendor);
        assertEquals("Test Vendor", savedVendor.getName());
    }

}
