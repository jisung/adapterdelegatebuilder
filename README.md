# AdapterDelegateBuilder

# AdapterDelegates
This is an add-on helper class for the [AdapterDelegates](https://github.com/sockeqwe/AdapterDelegates).

# How to use
```java

public class AnimalAdapter extends SimpleListDelegationAdapter<Animal> {
    
    public AnimalAdapter() {
        // Cat delegate
        addDelegate(createDelegateBuilder(Cat.class)
                .viewLayout(R.layout.item_cat)
                .viewBinder((vh, cat) -> {
                    vh.getView(R.id.name, TextView.class).setText(cat.getName());
                })
                .build());

        // Dog delegate
        addDelegate(createDelegateBuilder(Dog.class)
                .viewLayout(R.layout.item_dog)
                .viewBinder((vh, dog) -> {
                    vh.getView(R.id.name, TextView.class).setText(dog.getName());
                })
                .build());
    }
}

```

## Dependencies

[![](https://jitpack.io/v/jisung/adapterdelegatebuilder.svg)](https://jitpack.io/#jisung/adapterdelegatebuilder)

```groovy
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

```groovy
dependencies {
    compile 'com.github.jisung:adapterdelegatebuilder:3.0.3'
}
```

## License

```
Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
