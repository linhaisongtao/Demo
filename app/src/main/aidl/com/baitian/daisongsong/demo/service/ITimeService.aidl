// ITimeService.aidl
package com.baitian.daisongsong.demo.service;

// Declare any non-default types here with import statements

interface ITimeService {

    long getCurrentTime();

    long getProcessId();

    long getThreadId();
}
