
# react-native-crasheye

[![NPM](https://nodei.co/npm/react-native-crasheye.png?downloads=true&downloadRank=true&stars=true)](https://nodei.co/npm/react-native-crasheye/)

## Getting started

`$ npm install react-native-crasheye --save`

### Mostly automatic installation

`$ react-native link react-native-crasheye`

### Manual installation


#### iOS

1. In XCode, in the project navigator, right click `Libraries` ➜ `Add Files to [your project's name]`
2. Go to `node_modules` ➜ `react-native-crasheye` and add `RNCrasheye.xcodeproj`
3. In XCode, in the project navigator, select your project. Add `libRNCrasheye.a` to your project's `Build Phases` ➜ `Link Binary With Libraries`
4. Run your project (`Cmd+R`)

#### Android

1. Open up `android/app/src/main/java/[...]/MainActivity.java`
  - Add `import com.cooclsee.crasheye.RNCrasheyePackage;` to the imports at the top of the file
  - Add `new RNCrasheyePackage()` to the list returned by the `getPackages()` method
2. Append the following lines to `android/settings.gradle`:
  	```
  	include ':react-native-crasheye'
  	project(':react-native-crasheye').projectDir = new File(rootProject.projectDir, 	'../node_modules/react-native-crasheye/android')
  	```
3. Insert the following lines inside the dependencies block in `android/app/build.gradle`:
  	```
      compile project(':react-native-crasheye')
  	```


## Usage
```javascript
import Crasheye from 'react-native-crasheye';

// TODO: What to do with the module?
Crasheye;
```
  