﻿# contact-list-demo-android

## Build tools & versions used

- Android SDK Build tools 34-rc2
- Android Studio Electric Eel | 2022.1.1 Patch 1

## Steps to run the app

- Clone the repo.
- Open the project and run it like any other Android project :).
- You can change the base URL by modifying buildConfigField named "BASE_URL" in buildTypes at build.grade file. 
```
buildTypes {
        debug {
            buildConfigField "String", "BASE_URL", "\"https://s3.amazonaws.com/sq-mobile-interview/\""
        }
```

## What areas of the app did you focus on?

- Architecture

## What was the reason for your focus? What problems were you trying to solve?

I focused on app architecture because it helps to make easier implement unit test. It's more scalable and easier to maintain.

## How long did you spend on this project?

I spent 5 - 6 hours

## Did you make any trade-offs for this project? What would you have done differently with more time?

Probably I would make a better UI, with a better dimen management because I have a lot of hardcoded dimens there. 

## What do you think is the weakest part of your project?

I think theme management and UI are the weakest parts of my project. 

## Did you copy any code or dependencies? Please make sure to attribute them here!

No, I didn't copy any code.
I used libraries like Retrofit, Coil, Hilt, and Swiper Refresh

## Is there any other information you’d like us to know?
