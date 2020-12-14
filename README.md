# WeatherAppMVVM
As the name suggests it is the weather app,which hits the weather api.

##  OverView

* The main library used in this application are:
  * Retrofit
  * MVVM(Model-View-View Model)
  * Co-routines
  
## MVVM MODEL
The Model-View-ViewModel Pattern.

The main players in the MVVM pattern are:

1.The View — that informs the ViewModel about the user’s actions.

2.The ViewModel — exposes streams of data relevant to the View.

3.The DataModel — abstracts the data source. The ViewModel works with the DataModel to get and save the data.

## Retrofit
 Retrofit is a REST API Client for Java. It is developed by Square Inc. and uses OkHttp library for HTTP Request. It is a simple library that is used for network transaction. It is a very easy and fast library to retrieve and upload the data via Rest based web service.
 Automatic JSON parsing is done using GSON library.
 3 classes are required :
 * Model class - Json model is kept here.
 * Interfaces - all possible http operations are listed here.
 * Retrofit builder class - Instances which uses the interface and buiilder API wo allow defining the URL end points for HTTP operations. 

## Co-Routines
  * Used for asynchronous programming.
  * Dispatcher is used along with withContext to let the application know on which thread it have to resume.There are three type of dispatchers available:
  * Main - Runs on main android thread
  * IO - Any network operation or for interacting with UI
  * Default - CPU intensive work outside of main thread.
  

* There is a file json named "city_json" in main->assets->city_json,where all information regarding the id,name and country is stored.

* The Extensions.kt in java->utils->Extensions.kt file is used to store the date and time in the format dd MMM, yyyy - hh:mm a where 'a' depicts AM/PM in the UI using Calender and TimeZone instance.

* The temperature is returned back in Kelvin which is converted back to celcius using the formula.

* Convert 300 Kelvin to degrees Celsius:
 (°C) = 300K - 273.15 = 26.85 °C
 
  

### General Flow of Application
On the click of button "View Weather" the the api gets hit and the data from api is get back as response into weatherInfoData class.
Then the class calls the api interface by passing the city id as the parameter to api interface then the data is passed to weather data response class.
And there the data gets stored in UI

* The pre-built framework is used for UI.

## Build the Project

To build the project on your own follow these steps:

Clone the project
Get an API key from Weather Api.


Build the project!

[
![Screenshot 2020-12-12 at 10 37 28 PM](https://user-images.githubusercontent.com/38380683/101991781-e523e400-3cd4-11eb-847c-42b42fa076a7.png)
![Screenshot 2020-12-12 at 10 37 41 PM](https://user-images.githubusercontent.com/38380683/101991783-e81ed480-3cd4-11eb-8235-28132c0e59e6.png)
](url)
