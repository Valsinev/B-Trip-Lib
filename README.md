# B-Trip-Lib
This library is made to draw on a blank image document with user data passed to a Form class

How to use it:
1.You need to implement some interfaces:
  -IConfiguration - for configuring the font of the document, font color, daily money without hotel, daily money         with hotel, number of days in one order document, the path to the order img resource, the path to the travel img     resource,
    and the path for the order days img resource(this is a img with the rest of the days of the order because days       in one order are limited)

  -IOrderTextCoordinates - for the coordinates of the text data in the order list
  -ITravelListTextCoordinates - for the coordinates of the text data in the travel list
  -IOrderAdditionalDaysCoordinates - for the additional days of the order days document

  -BusinessTripForm - this is the interface for the form data

2. Set your form clas List<Integer> days with [form class].setDays(BTripGetDaysFromCheckboxesOrFields.getDays([form class])) if you need to set it else getDays(BTripGetDaysFromCheckboxesOrFields.getDays(this))

3. You can validate fields with utility.FieldValidator.class

4. You can calculate expences with utility.ExpenseCalculator class
 
5. Create empty collection for BufferedImage like: List<BufferedImage> images = new ArrayList<>();

6. Create TripTypeSelector class instance and pass OrderTextCoordinates, OrderAdditionalDaysCoordinates, TravelListTextCoordinates classes to the constructor:             
TripTypeSelector tripTypeSelector = new TripTypeSelector(new OrderTextCoordinates(), new OrderAdditionalDaysCoordinates(), new TravelListTextCoordinates());


7. Pass the BufferedImage collection and the form class instance to the selector class: TripTypeSelector.select([form class], images, [configuration class instance]);  - this populates a blank image with the form data at the correct places

8. Process the populated collection with images as you please :)
