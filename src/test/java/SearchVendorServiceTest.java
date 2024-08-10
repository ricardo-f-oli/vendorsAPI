import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

import com.example.vendorsAPI.domain.entities.Vendor;
import com.example.vendorsAPI.domain.entities.enums.ContractTypeEnum;
import com.example.vendorsAPI.domain.repository.VendorRepository;
import com.example.vendorsAPI.domain.service.SearchVendorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class SearchVendorServiceTest {

    @Mock
    private VendorRepository vendorRepository;

    @InjectMocks
    private SearchVendorService searchVendorService;

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
    void testFindVendorById_Success() {
        when(vendorRepository.findById(anyLong())).thenReturn(Optional.of(vendor));
        Optional<Vendor> foundVendor = searchVendorService.findVendorById(1L);
        assertTrue(foundVendor.isPresent());
        assertEquals(1L, foundVendor.get().getId());
        assertEquals("Test Vendor", foundVendor.get().getName());
    }

    @Test
    void testFindVendorById_NotFound() {
        when(vendorRepository.findById(anyLong())).thenReturn(Optional.empty());
        Optional<Vendor> foundVendor = searchVendorService.findVendorById(1L);
        assertFalse(foundVendor.isPresent());
    }

    @Test
    void testFindVendorsByRegistration_Success() {
        when(vendorRepository.findByRegistration(anyString())).thenReturn(Optional.ofNullable(vendor));
        Vendor foundVendor = searchVendorService.findByRegistration("1-CLT");
        assertEquals("Test Vendor", foundVendor.getName());
    }

    @Test
    void testFindVendorsByContractType_Success() {
        when(vendorRepository.findByContractTypeEnum(ContractTypeEnum.CLT)).thenReturn(Arrays.asList(vendor));
        List<Vendor> foundVendor = searchVendorService.findByContractType("CLT");
        assertEquals("Test Vendor", foundVendor.getFirst().getName());
    }
}
