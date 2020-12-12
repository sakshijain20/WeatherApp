package com.system.weatherapp.features.weather_info_show.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.system.weatherapp.R
import com.system.weatherapp.features.weather_info_show.model.WeatherInfoShowModel
import com.system.weatherapp.features.weather_info_show.model.WeatherInfoShowModelImpl
import com.system.weatherapp.features.weather_info_show.model.data_class.City
import com.system.weatherapp.features.weather_info_show.model.data_class.WeatherData
import com.system.weatherapp.features.weather_info_show.viewmodel.WeatherInfoViewModel
import com.system.weatherapp.utils.convertToListOfCityName
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_input_part.*
import kotlinx.android.synthetic.main.layout_sunrise_sunset.*
import kotlinx.android.synthetic.main.layout_weather_additional_info.*
import kotlinx.android.synthetic.main.layout_weather_basic_info.*

class MainActivity : AppCompatActivity() {

    private lateinit var model: WeatherInfoShowModel
    private lateinit var viewModel: WeatherInfoViewModel

    private var cityList: MutableList<City> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // initialize model. 
        model = WeatherInfoShowModelImpl(applicationContext)
        // initialize ViewModel
        viewModel = ViewModelProviders.of(this).get(WeatherInfoViewModel::class.java)

        // set LiveData and View click listeners before the call for data fetching
        setLiveDataListeners()
        setViewClickListener()

        /**
         Fetch city list when Activity open.
         */
        viewModel.getCityList(model)
    }

    private fun setViewClickListener() {
        // View Weather button click listener
        btn_view_weather.setOnClickListener {
            val selectedCityId = cityList[spinner.selectedItemPosition].id
            viewModel.getWeatherInfo(selectedCityId, model) // fetch weather info
        }
    }

    private fun setLiveDataListeners() {

        
        viewModel.cityListLiveData.observe(this, object : Observer<MutableList<City>>{
            override fun onChanged(cities: MutableList<City>) {
                setCityListSpinner(cities)
            }
        })

        /**
         * If ViewModel failed to fetch City list from data source, this LiveData will be triggered.
         * Here I've used lambda expression to implement Observer interface in second parameter.
         */
        viewModel.cityListFailureLiveData.observe(this, Observer { errorMessage ->
            Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()
        })

        //Progress Bar
        viewModel.progressBarLiveData.observe(this, Observer { isShowLoader ->
            if (isShowLoader)
                progressBar.visibility = View.VISIBLE
            else
                progressBar.visibility = View.GONE
        })

        
        viewModel.weatherInfoLiveData.observe(this, Observer { weatherData ->
            setWeatherInfo(weatherData)
        })

        /**
         * If ViewModel faces any error during Weather Info fetching API call by Model, then PUSH the
         * error message into `weatherInfoFailureLiveData`. After that, this method will be triggered.
         * Then we will hide the output view and show error message on UI
         */
        viewModel.weatherInfoFailureLiveData.observe(this, Observer { errorMessage ->
            output_group.visibility = View.GONE
            tv_error_message.visibility = View.VISIBLE
            tv_error_message.text = errorMessage
        })
    }

    private fun setCityListSpinner(cityList: MutableList<City>) {
        this.cityList = cityList

        val arrayAdapter = ArrayAdapter(
            this,
            R.layout.support_simple_spinner_dropdown_item,
            this.cityList.convertToListOfCityName()
        )

        spinner.adapter = arrayAdapter
    }

    private fun setWeatherInfo(weatherData: WeatherData) {
        output_group.visibility = View.VISIBLE
        tv_error_message.visibility = View.GONE

        tv_date_time?.text = weatherData.dateTime
        tv_temperature?.text = weatherData.temperature
        tv_city_country?.text = weatherData.cityAndCountry
        Glide.with(this).load(weatherData.weatherConditionIconUrl).into(iv_weather_condition)
        tv_weather_condition?.text = weatherData.weatherConditionIconDescription

        tv_humidity_value?.text = weatherData.humidity
        tv_pressure_value?.text = weatherData.pressure
        tv_visibility_value?.text = weatherData.visibility

        tv_sunrise_time?.text = weatherData.sunrise
        tv_sunset_time?.text = weatherData.sunset
    }
}
