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
# Data Model
    -This is a data class for modelling our data coming from repository which either loads data from network or local database.
    -We crerated a data class called DailyForcast
# Repository 
    -We have defined ForecastRepository.kt for Repository.
    -Our repository does 2 things: PART B. load data and model into data class.
                                   PART A. Provides data to our activity.
                                   
    -PART B. For loading data and modeling it into the Data class;
          - we are using a method to generate random temperature value for 7 days: loadForecast 
          which generates random tem value in a list called randomTemperature.
          -Later we are Mapping this list of temperatures to DailyForecast data class, with description string, 
           using map collection method. map returns a list of type DailyForecast which is referred by the variable forecastItems.
    -PART A. To provide data to our activity we are setting up LiveData; 1 mutableLiveData and 1 immutable LiveData
             which is being set by the loadForecast()
            - After setting up LiveData in loadForecast, we need to set up observe() and Observer(optional, an anonymous class object for
              Observer can be passed in to the observe method directly). These has to be used by the LiveData.
    
    Q LiveData deprecated? 
# Week 3 Summary
      -We implemented a a recycler view, An adapter using ListAdapter, ViewHolder and layout for binding data item with viewholder,.
      -We efficiently binded the data item wih the viewholder and implemented a clickhandler too.
      -we added a function getTempDescription() to get customized description about the temperature.

# Week 4 Intents, Menu/dialogues, shared preferences and multiple activity
       This week we will;
       -create a new Activity ForecastDetailsActivity
       -Using an intent we will pass 'clicked forecast data' to ForecastDetails Activity and display it.
       -Create a menu with single item to control our temp display units.
       -Show an alert dialogue when settings item is clicked to select display setting.
       -Save data for setting using SharedPreferences out of different options we have for saving data. 
        To save data we can use Databases, Files or Sharedpreferences.
                      Databases: it supports large, complex quesries.
                      Files:   It is generally used to save media files and documents.
                               it can also be used to save data for settings or data to be used by our apps.
                      SharedPreferences: for saving simple data, like settings.
                      
       -Update UI formatting based on selected setting.
       
       links for note on Intent: https://docs.google.com/document/d/1fEfZFK6OGns-hRqKRs-6FinxzwFDWqNhNq34sCZcOYo/edit
       
       - Created a new package called details.
       - added a new Activity called ForecastDetailsActivity.kt in it
       - added an Explicit intent inside the clickHandler method(An anonymous class which is being passed as an arguement) of DailyForecastAdapter

       - Added a passForecastDetailsUsingIntent() method for the purpose of making an intent instance in order to navigate to 
       ForecastDetailsActivity 
                    - here we are creating an instance of intent.
                    - with intent we are adding some data which will travel with the intent using putExtra()
                    - in ForecastDetailsActivity class we can access the data travelling with intent instance using intent.getFloatExtra 
                    or getIntent.getFloatExtra()
       - we craeted a kotlin file called ForecastUtils in which we created a method called formatTempForDisplay() to create right format for 
       temperature value. It can be used across the project to create format for temp.
       
 #21/09/21:
         -Earlier I tried to add fragment to MainActivity using XML.
         But later I added fragment progmatically. For that I used following code
         supportFragmentManager
             .beginTransaction()
             .add(R.id.activeMainRootViewId, LocationEnteryFragment)
             .commit()
         This code will add the fragment to the MainActivity but the fragment will come over the other UI elements.
         To resolve this issue we have declared a view component in the activity_main.xml file; FrameLayout
 #7-10-21
        Starting to refactor for shifting Navigation on Navigation Architecture Component
 #11-10-21
           What is Navigation Component Architecture?
           Answer: NCA is a combination of libraries, tools, and plugins which help us manage fragments and navigation and Navigation UI under one roof.
           
           Benefits: 
            Simplifies Navigation setup.
            Automates Fragment transaction.
            Handles back stack.
            Typesafe argument passing.
            Handles transition Animation
            Simple deep link handling
            Puts navigation pattern in one place as a graph which can be seen visually.
           
           
           In our app, Why Navigation Architecture Component?
           We in this app are dealing with many fragments and we have to navigate through them. Till now we are doing this manually, or say we are doing the navigation by a self-made system in which we made an AppNavigator Interface or we are doing that using Intents.
                  Apart from that, we would like to introduce a lot of things like bottom navigation. And we will have to manage the back-stack and many things.
           Conclusion: It’s a difficult thing to manage Fragments and to navigate through them. So GOOGlE and ANDROID have provided us with a system called Navigation Architecture Component.