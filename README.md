## AmazingChicago: CHICAGO LANDMARK EXPLORATION
## App Description
### Application A1:  
* consists of a single activity containing two fragments - Landlistfragment, webViewFragment (extends fragment class of Andriod framework)
* The Landlistfragment consists of a list of Chicago landmarks. Users can select one of the landmarks. When this happens,
the selected item stays highlighted and the webViewFragment displays the web page of the highlighted
item.
* This app also defines an options menu with two items: (1) exit A1, and (2) launching the next
application, A2. A1 launches A2 by starting the main activity in A2
* In addition, application A1 maintains an action bar. The action bar shows the name
of the application (your choice), a shortcut action for starting A2, and the overflow area.

### Application A2: 
* Application A2 is a picture repository. Pictures are displayed A2’s main (and only) activity, which contains in an Android HorizontalScrollView. 
* The scroll view shows images from each of the landmarks listed in application A1. The images use the entire display of a mobile device when the device is in
portrait mode except for portions being cut off by an image’s scale type.
* A1 starts the main activity in A2 upon user request

## Inter-Fragment Communication within A1 using ViewModel
- Andriod uses MVVM design pattern form inter-fragment communication. In the MVVM architecture, the ViewModel class of Andriod acts as a link between the View (UI components, like fragments) and the Model (data or business logic).
The ViewModel stores and manages UI-related data in a lifecycle-conscious way, ensuring the data survives configuration changes.
- In this case, Landlistfragment is observed and any change in livedata is used to change the views of webViewFragment.

### Landlistfragment is visible initially. 
<img width="316" alt="Screenshot 2024-07-05 122733" src="https://github.com/Jaykakkad82/Project5_Jaykumar_Kakkad/assets/97722419/a04aa589-afef-4888-b6ed-078302d514e7">
<img width="188" alt="Screenshot 2024-07-05 121506" src="https://github.com/Jaykakkad82/Project5_Jaykumar_Kakkad/assets/97722419/c6ce7ee6-7796-41db-ba0c-abe98a7d2202">

### A click is observed and the webViewFragment appers
<img width="316" alt="Screenshot 2024-07-05 122922" src="https://github.com/Jaykakkad82/Project5_Jaykumar_Kakkad/assets/97722419/02e50579-c38d-480b-b7bb-6732d6490708">
<img width="322" alt="Screenshot 2024-07-05 122841" src="https://github.com/Jaykakkad82/Project5_Jaykumar_Kakkad/assets/97722419/3479188c-6e50-4322-95a3-0d5a5e74fe7e">

## Action bar and Options Menu
<img width="287" alt="Screenshot 2024-07-05 123012" src="https://github.com/Jaykakkad82/Project5_Jaykumar_Kakkad/assets/97722419/5090fae3-9266-477a-bbd1-28ca492c7b53">
<img width="318" alt="Screenshot 2024-07-05 122945" src="https://github.com/Jaykakkad82/Project5_Jaykumar_Kakkad/assets/97722419/9e981904-10f2-491c-ae33-482911a62096">


