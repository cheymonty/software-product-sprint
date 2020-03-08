// Copyright 2019 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

/**
 * Adds a random greeting to the page.
 */

function createMap() {
    var fillmore = {lat: 38.9974, lng: -77.0276};
    const map = new google.maps.Map(
        document.getElementById('map'),
        {center: fillmore, zoom: 16});
    
    const marker = new google.maps.Marker({position: fillmore, map: map, title: 'First Rico Nasty concert!'});
}

function addRandomGreeting() {
  const greetings =
      ['Law and Order: SVU', 'Degrassi', 'Big Mouth', 'Good Girls', 'South Park', 'Atypical'];

  // Pick a random greeting.
  const greeting = greetings[Math.floor(Math.random() * greetings.length)];

  // Add it to the page.
  const greetingContainer = document.getElementById('greeting-container');
  greetingContainer.innerText = greeting;
}

function fetchRandomGreeting() {
    const responsePromise = fetch('/data');
    responsePromise.then(handlePromise);
}

function handlePromise() {
    const textPromise = response.text();
    textPromise.then(addCommentsToDom);
}

function addCommentsToDom(comment) {
    const commentContainer = document.getElementById('comment-container');
    commentContainer.innerText = comment;
}

