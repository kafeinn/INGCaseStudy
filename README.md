# INGCaseStudy
Weather Forecast API
What does Little Weather Forecast API do ?
-----------------------------------------
Little Weather Forecast API uses the OpenWeather APIs free (https://openweathermap.org/api) service to retrieve the weather forecast of given cities. It provides the following information for the next 48 hours for every 3 hours in this time period: 
- maximum, 
- feels like temperatures, 
- humidity 
The service exposes a REST API to make requests for weather forecasts.

How to build and run the Little Weather Forecast API ?
-----------------------------------------------------
Little Weather Forecast API uses SpringBoot and JSON libraries and is a maven project. After opening the project in your favourite IDE (i.e. IntelliJ,...) you let maven to download the dependencies written in pom.xml and automatically build the project.
If you decide to use this API officialy, you must sign-up at https://openweathermap.org/ for a free account and create an API token in order to put in application.config file of Little Weather Forecast API.
You can see the API Key generation page below:

![image](https://github.com/kafeinn/INGCaseStudy/assets/15650911/b1885c9b-9dab-4f3a-b09e-1806ef89b782)

Little Weather Forecast API uses 5 day weather forecast data service which gathers forecast data for every other 3 hours.

![image](https://github.com/kafeinn/INGCaseStudy/assets/15650911/47d2ba68-1dbb-4cd0-8837-4812a2d07eb0)

After Little Weather Forecast API service is started, you can call it on your browser using rest api calls easily as in the examples seen below.
To call the API, the city name must be passed as an arguement as in the format below. 

http://localhost:8080/forecast/{cityname}

Some sample calls are given for you as:

http://localhost:8080/forecast/istanbul

http://localhost:8080/forecast/paris

The forecast data is in json format and it consists of;
- forecast date-time
- maximum, 
- feels like temperatures, 
- humidity
for the next 48 hours for every 3 hours

Example: istanbul

![image](https://github.com/kafeinn/INGCaseStudy/assets/15650911/17a400bd-030e-4e39-b0d4-e2da26bfe1e8)

Example: paris

![image](https://github.com/kafeinn/INGCaseStudy/assets/15650911/b6bae77e-663a-42de-b876-381c9ba26043)



