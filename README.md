
# react-native-crasheye

[![NPM](https://nodei.co/npm/react-native-crasheye.png?downloads=true&downloadRank=true&stars=true)](https://nodei.co/npm/react-native-crasheye/)

## Introduction

Crasheye react native module.

Base on:

- Crasheye Android SDK 2.2.1
- Crasheye iOS SDK 2.5.1

More infomation: [http://www.crasheye.cn/sdk](http://www.crasheye.cn/sdk)

## Getting started

`$ npm install react-native-crasheye --save`

### Mostly automatic installation

`$ react-native link react-native-crasheye`

> You should add crasheye SDK to your iOS project. Official tutorial is [here](http://www.crasheye.cn/help/iosskduse#overview-ios-sdk).

### Manual installation


#### iOS

1. In XCode, in the project navigator, right click `Libraries` ➜ `Add Files to [your project's name]`
2. Go to `node_modules` ➜ `react-native-crasheye` and add `RNCrasheye.xcodeproj`
3. In XCode, in the project navigator, select your project. Add `libRNCrasheye.a` to your project's `Build Phases` ➜ `Link Binary With Libraries`
4. Add crasheye SDK to your iOS project. Official tutorial is [here](http://www.crasheye.cn/help/iosskduse#overview-ios-sdk).
5. Run your project (`Cmd+R`)

#### Android

1. Open up `android/app/src/main/java/[...]/MainActivity.java`
  - Add `import com.cooclsee.crasheye.RNCrasheyePackage;` to the imports at the top of the file
  - Add `new RNCrasheyePackage()` to the list returned by the `getPackages()` method
2. Append the following lines to `android/settings.gradle`:
  	```
  	include ':react-native-crasheye'
  	project(':react-native-crasheye').projectDir = new File(rootProject.projectDir, '../node_modules/react-native-crasheye/android')
  	```
3. Insert the following lines inside the dependencies block in `android/app/build.gradle`:
  	```
      compile project(':react-native-crasheye')
  	```


## Usage

### Import
```javascript
import Crasheye from 'react-native-crasheye';
```

### Initialize SDK
```javascript
Crasheye.init({
	appKey: 'your appKey', // required
	channelID: '', // option
	appVersion: '', // option
	flushOnlyOverWiFi: false, // default false
	userIdentifier: '', // option
	logging: {
		lines: 1000, // option, less than 1000
		filter: '', // option
	}
});
```

### Get SDK Version
```javascript
Crasheye.getSDKVersion()
  .then(ver => console.log(ver));
// or
let ver = await Crasheye.getSDKVersion();
```

### Get Device ID
```javascript
Crasheye.getDeviceId()
  .then(deviceId => console.log(deviceId));
// or
let deviceId = await Crasheye.getDeviceId();
```

### Add Breadcrumb
```javascript
Crasheye.leaveBreadcrumb('the breadcrumb content');
```

### Send Exception
```javascript
Crasheye.sendScriptExceptionRequestWithTitle('errorTitle', 'exception content');
```

### Add Extra Data
```javascript
Crasheye.addExtraData('key', 'value');
```
