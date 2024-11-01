# Vehicle Package

## Overview

This package represents a manual vehicle transmission, simulating the actions and statuses of a transmission system. 
It includes the `ManualTransmission` interface that defines core methods and behaviors for a manual transmission system, 
as well as the `RegularManualTransmission` class, which provides an implementation of this interface with specific 
speed and gear constraints.

---

## Interface: `ManualTransmission`

### Overview

The `ManualTransmission` interface represents the manual actions a transmission can perform 
while operating a car, along with possible status messages based on the transmission’s state.

### Methods

- **`String getStatus()`**  
  Retrieves the current status of the transmission as a `String`, with possible values such as:
  - `"OK: everything is OK."`: Indicates that the speed or gear was changed successfully without the need to alter the other.
  - `"OK: you may increase the gear."`: Speed increased, and the new speed falls within the next gear’s range.
  - `"OK: you may decrease the gear."`: Speed decreased, and the new speed falls within the previous gear’s range.
  - `"Cannot increase speed, increase gear first."`: Speed increase is not possible without first increasing the gear.
  - `"Cannot decrease speed, decrease gear first."`: Speed decrease is not possible without first decreasing the gear.
  - `"Cannot increase gear, increase speed first."`: Gear increase is not possible without first increasing the speed.
  - `"Cannot decrease gear, decrease speed first."`: Gear decrease is not possible without first decreasing the speed.
  - `"Cannot increase speed. Reached maximum speed."`: Indicates that the maximum speed limit has been reached.
  - `"Cannot decrease speed. Reached minimum speed."`: Indicates that the speed cannot be decreased further as it is already at zero.
  - `"Cannot increase gear. Reached maximum gear."`: Indicates that the gear cannot be increased as it is already at the highest gear (gear 5).
  - `"Cannot decrease gear. Reached minimum gear."`: Indicates that the gear cannot be decreased as it is already at the lowest gear (gear 1).

- **`int getSpeed()`**  
  Gets the current speed of the vehicle.

- **`int getGear()`**  
  Gets the current gear of the vehicle.

- **`ManualTransmission increaseSpeed()`**  
  Increases the speed without changing gears, returning the same object if the speed cannot be increased.

- **`ManualTransmission decreaseSpeed()`**  
  Decreases the speed without changing gears, returning the same object if the speed cannot be decreased.

- **`ManualTransmission increaseGear()`**  
  Increases the gear without changing the speed, returning the same object if the gear cannot be increased.

- **`ManualTransmission decreaseGear()`**  
  Decreases the gear without changing the speed, returning the same object if the gear cannot be decreased.

---

## Class: `RegularManualTransmission`

### Overview

The `RegularManualTransmission` class implements `ManualTransmission`, simulating a manual transmission with defined speed ranges for each gear.

And other predefined messages based on speed and gear limitations.
