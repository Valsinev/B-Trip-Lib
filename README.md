# B-Trip-Lib

How to use it:

1. You need a form class to implement the interface BusinessTripForm: public class Form implements BusinessTripForm 
2. Set your form clas List<Integer> days with [form class].setDays(BTripGetDaysFromCheckboxesOrFields.getDays([form class])) if you need to set it else getDays(BTripGetDaysFromCheckboxesOrFields.getDays(this))
3. Create empty collection for BufferedImage like: List<BufferedImage> images = new ArrayList<>();
4. Pass the BufferedImage collection to the selector class: TripTypeSelector.select(this, images);  - this populates a blank image with the form data at the correct places
5. Process the populated collection with images as you please :)
