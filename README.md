# Yt Manager

## Yt Manager is a FullStack web app on Java Spring Boot, React.js and MySQL

#### This amazing web app allows you to:
* Search from youtube
* Add Videos to Playlists
* Delete videos from playlists
* Create new Playlists
* Watch the videos
* Check off watched playlists
* Set a time "bookmark" for the video
* See which videos has bookmarks

### Online demo:
#### *soon*

#### How to set it up locally:
1. You need to have the JDK 11 or later installed
2. Run the Xampp Apache and MySQL
3. Use the MySQL workbench with example database "ytmanager_db.sql"
4. Run the Java Spring Boot app
5. Run the Frontend with command "npm start"
6. Use it in your browser

#### Future features:
* reordering the videos
* delete the video from the db if it is not assigned to any playlist
* changing the video order if it is watched to go to the end of the playlist
* adding the watched video to the playlist "watched"

## Demo
Here is what we see as we run our app. The navbar on the left side with already created playlists, and the search bar to search new videos to save
![Zrzut ekranu 2024-04-23 181708](https://github.com/DanielPawlowicz/Yt-Manager-FullStack/assets/91285163/423d5ce4-9664-4649-8bc7-3fc5bcd74dcb)

After the button next to the search input is clicked, we can see a list of videos searched from youtube, with the buttons to add them to the playlists
![Zrzut ekranu 2024-04-23 181819](https://github.com/DanielPawlowicz/Yt-Manager-FullStack/assets/91285163/77ae2b41-3667-4af4-8d73-98e94f24a95c)

When we click  " To Watch button" the video will be added to playlist "To Watch"
But if we put the cursor over the "+ Playlists" button there will show a dialog box with already existing playlists, that when clicked the video will be added to them. But over them, there is a input to create new playlist, and add video to it.
![Zrzut ekranu 2024-04-23 182347](https://github.com/DanielPawlowicz/Yt-Manager-FullStack/assets/91285163/d9635056-c436-47cd-a6a8-d11ab44f2f90)

Here is the video added to the playlist
![Zrzut ekranu 2024-04-23 181932](https://github.com/DanielPawlowicz/Yt-Manager-FullStack/assets/91285163/42a57ea5-d8a3-4527-94be-469d46c1406a)

If we have chosen different playlist than "To Watch", the video will be added to selected playlist, but also to "To Watch" playlist
![Zrzut ekranu 2024-04-23 182022](https://github.com/DanielPawlowicz/Yt-Manager-FullStack/assets/91285163/ba532b18-7775-49ea-93fa-29c872ec0e2a)

After clicking the video thumbnail on the playlist, we will be able to play our video
![Zrzut ekranu 2024-04-23 182456](https://github.com/DanielPawlowicz/Yt-Manager-FullStack/assets/91285163/40589fd8-dbfa-4f3b-a1c9-388e7a75f0b3)

If we click the "Add bookmark" button, under the video, the time of the bookmark will be set and visible on the list of the videos on playlist 
![Zrzut ekranu 2024-04-23 182525](https://github.com/DanielPawlowicz/Yt-Manager-FullStack/assets/91285163/4e3ea7be-76e3-476e-b633-1a92d0d8a534)

And the next time we click the video, the bookmark time will be visible above the button. When we click the time, the video starts playing from this exact moment
![Zrzut ekranu 2024-04-23 182546](https://github.com/DanielPawlowicz/Yt-Manager-FullStack/assets/91285163/84773e8a-e030-4243-9a53-4fde0f41dba0)

When we click the "set watched" button, next to the video, the video will be made grayscaled. We can make it "unwatched" by clicking one more time on the button
![Zrzut ekranu 2024-04-23 182613](https://github.com/DanielPawlowicz/Yt-Manager-FullStack/assets/91285163/748e04ac-7fee-4b49-aca3-de61b0158d23)

All properties we set for the video are visible on the other playlists as well (bookmarks, if it is watched)
![Zrzut ekranu 2024-04-23 182647](https://github.com/DanielPawlowicz/Yt-Manager-FullStack/assets/91285163/39e71389-c8e1-4ff6-871b-57d72d9b108d)



