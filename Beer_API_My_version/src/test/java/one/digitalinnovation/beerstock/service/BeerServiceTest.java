package one.digitalinnovation.beerstock.service;

import one.digitalinnovation.beerstock.builder.BeerDTOBuilder;
import one.digitalinnovation.beerstock.dto.BeerDTO;
import one.digitalinnovation.beerstock.entity.Beer;
import one.digitalinnovation.beerstock.exception.BeerAlreadyRegisteredException;
import one.digitalinnovation.beerstock.mapper.BeerMapper;
import one.digitalinnovation.beerstock.repository.BeerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BeerServiceTest {

    @Mock
    private BeerRepository beerRepository;

    private final BeerMapper beerMapper = BeerMapper.INSTANCE;

    @InjectMocks
    private  BeerService beerService;

    @Test
    void QuandoBeerInformadaUmaBeerDeveSerCriada() throws BeerAlreadyRegisteredException{
    //given
    BeerDTO beerDTO = BeerDTOBuilder.builder().build().toBeerDTO();
    Beer expectedCervejaSalva = beerMapper.toModel(beerDTO);

    //when
    when(beerRepository.findByName(beerDTO.getName())).thenReturn(Optional.empty());
    when(beerRepository.save(expectedCervejaSalva)).thenReturn(expectedCervejaSalva);

    //then
        BeerDTO createBeerDTO = beerService.createBeer(beerDTO);

        assertThat(createBeerDTO.getId(), is(equalTo(beerDTO.getId())));
        assertThat(createBeerDTO.getName(), is(equalTo(beerDTO.getName())));
        assertThat(createBeerDTO.getMax(), is(equalTo(beerDTO.getMax())));
        assertThat(createBeerDTO.getType(), is(equalTo(beerDTO.getType())));
        assertThat(createBeerDTO.getQuantity(), is(greaterThan(2)));


    }
}
