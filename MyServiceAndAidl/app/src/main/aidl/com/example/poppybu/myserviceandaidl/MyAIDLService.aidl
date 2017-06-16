// MyAIDLService.aidl
package com.example.poppybu.myserviceandaidl;

// Declare any non-default types here with import statements

interface MyAIDLService {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    int plus(int a,int b);
    String toUpperCase(String str);
}
