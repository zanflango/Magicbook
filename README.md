Magicbook
=========

Minecraft mod for Hypixel VampireZ - Magicbook / Holy book which adds an effect to other players in a radius if the book is held. The idea is to creat sort of a healer / priest in the game useing this item. The code is kind or rought but I'm still learning minecraft modding. you will need to give the users a book titles "Book of ... " for example "Book of Healing" or something similar. the code uses the book to keep track of the timers, as well as the effect of the book. page 1 of the book is used as a timer, so start it at 0, page 2 is the potion effect to be applied, page 2 is the durration of the effect and page 4 is the level of the potion effect. currently the code is hard coded to trigger the effect if the users holds the book for 20-25 sconds. the code checks for the book in players hands ever 5 seconds and updates a counter. so there is a little bit of wiggle room on the time. The distance of the effect is also hard coded to 5 blocks. It would be pretty eash to add the effect distance and hold durration to the book as well and would require just a few minor mods to the code. 
The player holding the book must do nothing else but hold the book for the alloted time for the effect to take place. If the player left clicks with the book on anything / hits something the coutner gets reset. the player can right click on doors and without reseting the timer. 
z
* /give zanflango written_book 1 0 {title:"Book of Healing",pages:["0","REGENERATION","100","0"]}
* /give zanflango written_book 1 0 {title:"Book of Speed",pages:["0","SPEED","100","2"]}
* /give zanflango written_book 1 0 {title:"Book of Strength",pages:["0","INCREASE_DAMAGE","100","2"]}
    */
