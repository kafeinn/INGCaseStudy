package com.ing.casestudy.LittleWeatherForecast;

import com.ing.casestudy.LittleWeatherForecast.model.IForecastData;
import com.ing.casestudy.LittleWeatherForecast.model.LittleForecastData;
import com.ing.casestudy.LittleWeatherForecast.service.WeatherForecastService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LittleWeatherForecastApplicationTests {

	@InjectMocks
	private WeatherForecastService weatherService;

	@Mock
	private RestTemplate restTemplate;

	@Test
	public void testGetForecastDataFromService() throws ParseException {

		List<IForecastData> forecastDataList = weatherService.getForecastDataFromService("istanbul");

		assertNotNull(forecastDataList);
		assertFalse(forecastDataList.isEmpty());
	}

	@Test
	public void testCheckWeatherDateInReportRangeSuccess() {
		WeatherForecastService weatherService = WeatherForecastService.getInstance();

		// Geçerli bir tarih verildiğinde true dönmeli
		assertTrue(weatherService.CheckWeatherDateInReportRange(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))));

		// Gelecekte bir tarih verildiğinde true dönmeli
		assertTrue(weatherService.CheckWeatherDateInReportRange(LocalDateTime.now().plusHours(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))));
	}

	@Test
	public void testCheckWeatherDateInReportRangeFail() {
		WeatherForecastService weatherService = WeatherForecastService.getInstance();

		// Geçmiş bir tarih verildiğinde false dönmeli
		assertFalse(weatherService.CheckWeatherDateInReportRange("2022-12-27 12:00:00"));
	}



	@Test
	public void testGetLittleForecastData() {
		WeatherForecastService weatherService = WeatherForecastService.getInstance();
		LittleForecastData littleForecastData = weatherService.getLittleForecastData(25, 20.0, 70, "2023-12-27 12:00:00");

		assertEquals(25, littleForecastData.getTempMax());
		assertEquals(20.0, littleForecastData.getFeelsLike());
		assertEquals(70, littleForecastData.getHumidity());
		assertEquals("2023-12-27 12:00:00", littleForecastData.getDtTxt());
	}

	@Test
	public void testSetMainWeatherData() {
		WeatherForecastService weatherService = WeatherForecastService.getInstance();
		weatherService.SetMainWeatherData(25.0, 20.0, 1013, 70, 1013, 1015, 2, 28, 18, "2023-12-27 12:00:00");
	}

}
