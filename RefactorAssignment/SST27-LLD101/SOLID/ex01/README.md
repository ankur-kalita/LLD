# Exercise ex01

#1. I think in OrderService, creating ther emailClient violates the DIP, High level module should depend on abstraction, not complete implementation.
#2. If in future we want to change the tax calculation method, we have to violate OCP.
#3. Checkout method has too many functionalities, which violates the SRP.