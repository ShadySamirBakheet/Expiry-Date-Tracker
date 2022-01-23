# Expiry Date Tracker

## Challenge Details:
### The application should contain:
#### A. 3 screens (Home, Barcode Scanner, and Expired Items screen).
1) The Home screen provide a way to scan item’s barcode and a recycler view contains a card view for each scanned item which lists item details as follows:
Item name.
Item type/category like (Food, Drink, …etc).
Item expiry date.
2) The Barcode scanner screen which will be opened to scan the item’s barcode.
3) The Expired items screen which displays expired items in recycler view.
### II. The application should do the following:
#### A. Upon clicking on the scan barcode button, the app should scan the barcode of the selected item, then this item will be added to the recycler view in the main screen (the recycler view length should be at least 4 items).
#### B. The items in the main screen should be sorted by expiry date (the time left before the item is considered expired).
#### C. The app should show a descriptive notification when the item has been expired and the expired item should be deleted from the main screen and added to the expired items screen.
#### D. If the real expiry date period is more than 1 day, the app should provide a way to allow the user to mock setting the expiry date of the selected item to be as follows [round robin]:
Expiry date of the first item is 6 hours.
Expiry date of the second item is 12 hours.
Expiry date of the third item is 18 hours.
Expiry date of the fourth item is 24 hours.
#### E. The app should be able to keep displaying the scanned items even if the app is closed or the phone is rebooted.
#### F. The app should keep tracking of the expiry date of each item even if the app is closed or the phone is rebooted.
#### G. Ask for the proper permissions when needed

## Video and Screenshots for the working application

https://user-images.githubusercontent.com/33626785/150689641-4de8322e-1063-49af-8509-4b7dd216b504.mp4

![Notification](https://user-images.githubusercontent.com/33626785/150690534-3b9c3765-9f66-42e8-a9dc-e5b6d16d81c1.jpg)

## A working [APK.](https://github.com/ShadySamirBakheet/Expiry-Date-Tracker/blob/main/app-release.apk)

