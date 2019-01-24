An example project which uses:
* Serverless Framework
* AWS Lambda
* Kotlin
* Dagger

Dependencies of service and modules may not make sense. They are created just to demonstrate Dagger usage.

### Development


#### Kapt and Intellij

In order to make Kotlin Annotation Processing (kapt) work, you need to delegate build runs to intellij. 
1. Go to: `Build Tools -> Gradle -> Runner` 
2. Click `Delegate IDE build/run actions to gradle`
3. Apply changes.

Now each build operation will run kotlin annotation processing through gradle.