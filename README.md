ParkingManagingSystem
=====================

Task Seperating

1. Parking lot

(Given) One car, one Empty parking lot with 100 available parking places
(When) Park that car
(Then) Number of available parking place reduce 1, parking succeed.

(Given) One car, one parking lot that parked 100 cars
(When) Park that car
(Then) Number of available parking place doesn't change, parking failed.

(Given) One parking lot with 100 available parking places
(When) Park two cars and fetch two cars
(Then) Number of available parking places increase 2.

(Given) One parking lot with 100 available parking places
(When) Park two cars and fetch the first one
(Then) Fetch car is exactly the first one.

(Given) One parking lot with 100 available parking places
(When) Park one car and fetch a car that doesn't exist in the parking lot
(Then) Fetch no car.

(Given) One parking lot with 100 available parking places
(When) Park one car and fetch that car twice
(Then) The first fetching succeed, while the second fetching got no car.


2. Parking boy

(Given) One parking boy who manages 3 parking lot
(When) Park a car when no parking lot are full
(Then) Park that car in the first parking lot

(Given) One parking boy who manages 3 parking lot
(When) Park a car when first parking lot is full
(Then) Park that car in the second parking lot

(Given) One parking boy who manages 3 parking lot
(When) Park a car when first two parking lots are full
(Then) Park that car in the third parking lot

(Given) One parking boy who manages 3 parking lot
(When) Park a car when all parking lots are full
(Then) Parking fails

(Given) One parking boy who manages 3 parking lot
(When) Park one car in the first parking lot and fetch the car
(Then) Fetched exactly the same car parked.

(Given) One parking boy who manages 3 parking lot
(When) Park one car in the third parking lot , fetch another car from the first parking lot, then fetch the car parked in the third parking lot
(Then) Fetched exactly the same car parked in the third parking lot


3. Smart parking boy

(Given) One parking boy who manages 3 parking lot
(When) Park a car when the first parking lot contains the most available parking places.
(Then) Park that car in the first parking lot

(Given) One parking boy who manages 3 parking lot
(When) Park a car when the second parking lot contains the most available parking places.
(Then) Park that car in the second parking lot

(Given) One parking boy who manages 3 parking lot
(When) Park a car when the third parking lot contains the most available parking places.
(Then) Park that car in the third parking lot

(Given) One parking boy who manages 3 parking lot
(When) Park a car when all parking lots are full
(Then) Parking fails

(Given) One parking boy who manages 3 parking lot
(When) Park one car in the first parking lot and fetch the car
(Then) Fetched exactly the same car parked.

(Given) One parking boy who manages 3 parking lot
(When) Park one car in the third parking lot , fetch another car from the first parking lot, then fetch the car parked in the third parking lot
(Then) Fetched exactly the same car parked in the third parking lot


4. Super parking boy

(Given) One parking boy who manages 3 parking lot
(When) Park a car when the first parking lot has the highest available rate.
(Then) Park that car in the first parking lot

(Given) One parking boy who manages 3 parking lot
(When) Park a car when the second parking lot has the highest available rate.
(Then) Park that car in the second parking lot

(Given) One parking boy who manages 3 parking lot
(When) Park a car when the third parking lot has the highest available rate.
(Then) Park that car in the third parking lot

(Given) One parking boy who manages 3 parking lot
(When) Park a car when all parking lots are full
(Then) Parking fails

(Given) One parking boy who manages 3 parking lot
(When) Park one car in the first parking lot and fetch the car
(Then) Fetched exactly the same car parked.

(Given) One parking boy who manages 3 parking lot
(When) Park one car in the third parking lot , fetch another car from the first parking lot, then fetch the car parked in the third parking lot
(Then) Fetched exactly the same car parked in the third parking lot


5.Parking manager

(Given) One parking manger who leads a normal parking boy, a smart parking boy, and a super parking boy. All of those three parking boy manage three parking lot for each. The parking manager manages another two parking lots as well.
(When) Let the normal parking boy to park a car
(Then) Park that car in the parking lot managed by the normal parking boy. And that parking lot is the first available parking lot.

(Given) One parking manger who leads a normal parking boy, a smart parking boy, and a super parking boy. All of those three parking boy manage three parking lot for each. The parking manager manages another two parking lots as well.
(When) Let the smart parking boy to park a car
(Then) Park that car in the parking lot managed by the smart parking boy. And that parking lot contains the most available parking places.

(Given) One parking manger who leads a normal parking boy, a smart parking boy, and a super parking boy. All of those three parking boy manage three parking lot for each. The parking manager manages another two parking lots as well.
(When) Let the super parking boy to park a car
(Then) Park that car in the parking lot managed by the super parking boy. And that parking lot has the highest available rate.

(Given) One parking manger who leads a normal parking boy, a smart parking boy, and a super parking boy. All of those three parking boy manage three parking lot for each. The parking manager manages another two parking lots as well.
(When) Let the parking manger himself to park several cars
(Then) Cars would be parked randomly in those two parking lot that the parking manager manages.

(Given) One parking manger who leads a normal parking boy, a smart parking boy, and a super parking boy. All of those three parking boy manage three parking lot for each. The parking manager manages another two parking lots as well.
(When) Let the parking manger himself to park a car and then fetch it
(Then) Fetched exactly the same car parked.


6. Parking director

(Given) One parking director who leads a parking manager.
(When) Let the parking manger himself to park a car
(Then) In the output report, the number of available parking places for that parking lot managed by the parking manager reduce 1.

(Given) One parking director who leads a parking manager.
(When) Let the normal parking boy to park a car
(Then) In the output report, the number of available parking places for that parking lot managed by the normal parking boy reduce 1.

(Given) One parking director who leads a parking manager.
(When) Let the smart parking boy to park a car
(Then) In the output report, the number of available parking places for that parking lot managed by the smart parking boy reduce 1.

(Given) One parking director who leads a parking manager.
(When) Let the super parking boy to park a car
(Then) In the output report, the number of available parking places for that parking lot managed by the super parking boy reduce 1.
