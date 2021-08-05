# WeatherApp
Weather App following standard practices, standard App Architecture.

I am creating this app as part of my learning process. I have created some apps earlier. In this app I am taking care of the standard practices while making the app by-

# Focus Points
                 -Taking care of the Activity Lifecycle
                 -Using RecyclerView for dynamically showing data.
                 -Creating a Data Model
                 -Using Repositories
                 -LiveData
                 -MVVM App Architecture
                 -Using XML still for layout files (Probably will refactor into Android Jetpack,
                 Now don't want to increase the load)

# Creating a layout file using XML
               -created a proper layout file Activity_main.xml which has an ImageView for location icon,
               A textView to instruct to enter Zip code, an EditText to take User Input, and a button to
               act to show required output.


# Some General set ups
              -Layout file for MainActivity is inflated.
              -Onclick listener is set up
# Data Model and Repositories
# Data Model Class
    -This is a data class for modelling our data coming from repository which either loads data from network or local database.
    -We crerated a data class called DailyForcast
# Repository 
    -We have defined ForecastRepository.kt for Repository.
    -Our repository does 2 things: PART B. load data and model into data class; loading data can be done from internet/local repository.
                                   PART A. Provides data to our activity.
                                   
    -PART B. For loading data and modeling it into the Data class;
          - we are using a method to generate random temperature value for 7 days: loadForecast 
          which generates random 7 values in a list called randomTemperature.
          -Later we are Mapping this list of temperatures to DailyForecast data class, with description string, 
           using map collection method. map returns a list of type DailyForecast which is referred by the variable forecastItems.
    -PART A. To provide data to our activity we are setting up LiveData; 1 mutableLiveData and 1 immutable LiveData
             which is being set by the loadForecast().
            
            -LiveData is an observable class which has to be connected with observe method and instance of observer class.
            Following system helps the app to flow data in a very smooth form.
            "How is weather" button is clicked --> it invokes loadForecast() in ForecastRepository which generates 7 randowm teperature values and map 
            those in a list --> this list is being connected with mutableLiveData which is internally connected with a livedata --> In MainActivity.kt
            observe method and an instance of observer class will work upon any changes in the list of DailyForecast, forecastItems.
            -Inside the instace of observer class we use dailyForecastAdapter.submitList(it) which submits the new list.
            in a background thread it computes the differene between the lists if any was earlier being used. It internally invokes the 
            corresposning adapter which creates new ViewHolders, binds the items from the newly submitted list with the viewholders. This 
            reflects on the RV

            
            
    
   

