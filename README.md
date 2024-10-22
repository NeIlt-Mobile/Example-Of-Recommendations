# Example of Recommendations App

This app is a simple example of a recommendation system where users can like or dislike randomly generated items </br> (represented by strings of letters), and the system adjusts future recommendations based on the user's preferences.

## Features

- **Displays a list of random items, each consisting of a name (random string of letters).**
- **Users can like or dislike items by checking/unchecking a box next to each item.**
- **The app tracks which letters appear most frequently in liked items and uses that data to influence future recommendations.**
- **Floating action button to refresh and update the list of recommendations, prioritizing items containing liked letters.**

## Technology Stack

- **Jetpack Compose**: Used for building UI components.
- **ViewModel**: Manages the app's data and business logic.
- **MutableState and MutableStateMap**: Tracks the state of items and liked letters.

## How It Works

- Items are generated randomly when the app starts.
- Liking an item will update the internal letter preference system.
- Refreshing the list will regenerate items with priority given to items containing frequently liked letters.

## License [>](LICENSE.md)
```
   Copyright 2024 NeIlt-Mobile

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