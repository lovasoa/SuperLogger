package com.github.lovasoa.superlogger;

import com.sun.management.OperatingSystemMXBean;
import java.lang.management.ManagementFactory;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class SystemInfo 
{
    OperatingSystemMXBean osBean;

    public SystemInfo() {
        osBean = ManagementFactory.getPlatformMXBean(OperatingSystemMXBean.class);
    }

    public double cpu() {
        return osBean.getSystemCpuLoad();
    }

    public double memory() {
        return (double) osBean.getFreePhysicalMemorySize() /
            osBean.getTotalPhysicalMemorySize();
    }

    public String computerName() {
        try {
            return InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException ex) {
            return "unknown";
        }
    }

    public String ip() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException ex) {
            return "unknown";
        }
    }

    public String toString() {
        return "cpu: " + cpu() +
            "\nmemory: " + memory() +
            "\ncomputer: " + computerName() +
            "\nip: " + ip();
    }
}
