package com.system.weatherapp.features.weather_info_show.model

import com.system.weatherapp.common.RequestCompleteListener
import com.system.weatherapp.features.weather_info_show.model.data_class.City
import com.system.weatherapp.features.weather_info_show.model.data_class.WeatherInfoResponse

interface WeatherInfoShowModel {
    fun getCityList(callback: RequestCompleteListener<MutableList<City>>)
    fun getWeatherInfo(cityId: Int, callback: RequestCompleteListener<WeatherInfoResponse>)
}