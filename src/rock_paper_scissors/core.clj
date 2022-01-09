(ns rock-paper-scissors.core
  (:gen-class))

;; *** Data structure ***

(def 
  rules
  {
   "r"{
       :label "rock"
       :wins_to "s"
      },
   "p" {
       :label "paper"
       :wins_to "r"
      },
   "s" {
       :label "scissors"
       :wins_to "p"
      }
   })

;; *** Helper functions ***

(defn get-computer-choice
  "Gets a random choice for computer."
  []
  (rand-nth (keys rules)))

(defn player-win?
  "0 if it is a tie. 1 if player wins. -1 if player loses."
  [player computer]
  (if (= player computer)
    0
    (if (= (get-in rules [player :wins_to]) computer) 1 -1)))

(defn player-message
  "Gets a human-readable message for player."
  [result]
  (case result
    0 "Its a tie"
    1 "You won!"
    -1 "You lost!"))

;; *** Side effects functions ***

(defn get-player-choice
  "Gets and validates player choice."
  []
  (println "What's your choice? 'r' for rock, 'p' for paper, 's' for scissors")  
  (let [input (read-line)]
    (if (contains? rules input)
      input      
      (do
        (println "Invalid choice. Try again.")
        (get-player-choice)))))

(defn play
  "Starts a new game."
  []
  (let [player (get-player-choice)
        computer (get-computer-choice)]
    (println (player-message (player-win? player computer)))
    (println "Computer choice: " computer)))

(defn -main
  [& args]
  (println "Get ready to play rock, papers and scissors!")
  (play))
