# WeatherAppMVVM
As the name suggests it is the weather app,which hits the weather api.

# Description

* The MVVM model is used in this application.
# MVVM MODEL
The Model-View-ViewModel Pattern
The main players in the MVVM pattern are:

1.The View — that informs the ViewModel about the user’s actions
2.The ViewModel — exposes streams of data relevant to the View
3.The DataModel — abstracts the data source. The ViewModel works with the DataModel to get and save the data.

On the click of button "View Weather" the the api gets hit and the data from api is get back as response into weatherInfoData class.
Then the class calls the api interface by passing the city id as the query parameter to api interface then the data is passed to weather data response class.
And there the data gets stored in UI

* The UI is broken into multiple parts for the single screen.
