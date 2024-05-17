import random

def displayIntro():
    print("""
_______________________________________________
 _    _                                         
| |  | |                                        
| |__| | __ _ _ __   __ _ _ __ ___   __ _ _ __  
|  __  |/ _` | '_ \ / _` | '_ ` _ \ / _` | '_ \ 
| |  | | (_| | | | | (_| | | | | | | (_| | | | |
|_|  |_|\__,_|_| |_|\__, |_| |_| |_|\__,_|_| |_|
                     __/ |                      
                    |___/                       
_______________________________________________
_____________________Rules_____________________
Try to guess the hidden word one letter at a   
time. The number of dashes are equivalent to   
the number of letters in the word. If a player 
suggests a letter that occurs in the word,     
blank places containing this character will be 
filled with that letter. If the word does not  
contain the suggested letter, one new element  
of a hangman's gallow is painted. As the game  
progresses, a segment of a victim is added for 
every suggested letter not in the word. Goal is
to guess the word before the man hangs!        
_______________________________________________
""")

def displayEnd(result):
    if result:
        print("""
________________________________________________________________________
 _                                         _                            
(_)                                       (_)                           
 _  _   _  ___  _   _  __      _____  _ __ _ ____  _   _  ___  _   _ _ 
| || | | |/ _ \| | | | \ \ /\ / / _ \| '__| |_  / | | | |/ _ \| | | (_)
| || |_| | (_) | |_| |  \ V  V / (_) | |  | |/ /  | |_| | (_) | |_| |_ 
|_| \__, |\___/ \__,_|   \_/\_/ \___/|_|  |_/___|  \__, |\___/ \__,_(_)
     __/ |                                          __/ |              
    |___/                                          |___/               
________________________________________________________________________
""")
    else:
        print("""
__   __            _           _   _   _          
\ \ / /           | |         | | | | | |         
 \ V /___  _   _  | | ___  ___| |_| | | |         
  \ // _ \| | | | | |/ _ \/ __| __| | | |         
  | | (_) | |_| | | | (_) \__ \ |_|_| |_|         
  \_/\___/ \__,_| |_|\___/|___/\__(_) (_)         
 _______ _                                     _ 
|__   __| |                                   | |
   | |  | |__   ___    ___  _ __   ___   __ _ | |
   | |  | '_ \ / _ \  / _ \| '_ \ / _ \ / _` || |
   | |  | | | |  __/ | (_) | | | | (_) | (_| ||_|
   |_|  |_| |_|\___|  \___/|_| |_|\___/ \__,_|(_)
__________________________________________________________________________
""")

def displayHangman(lives):
    stages = [
        """
           --------
           |/     |
           |      O
           |     \\|/
           |      |
           |     / \\
           -
        """,
        """
           --------
           |/     |
           |      O
           |     \\|/
           |      |
           |     /
           -
        """,
        """
           --------
           |/     |
           |      O
           |     \\|/
           |      |
           |
           -
        """,
        """
           --------
           |/     |
           |      O
           |     \\|
           |      |
           |
           -
        """,
        """
           --------
           |/     |
           |      O
           |      |
           |      |
           |
           -
        """,
        """
           --------
           |/     |
           |      O
           |
           |
           |
           -
        """
    ]
    print(stages[lives])

def getWord():
    words = []
    with open("hangman-words.txt", "r") as file:
        words = file.read().splitlines()
    return random.choice(words)

def valid(char):
    return char.isalpha() and len(char) == 1

def play():
    word = getWord()
    print(word)
    word_letters = set(word)
    guessed_letters = set()
    lives = 5

    while len(word_letters) > 0 and lives > 0:
        print("Guess the word:", end=" ")
        for char in word:
            if char in guessed_letters:
                print(char, end="")
            else:
                print("_", end="")
        print()

        displayHangman(lives)

        guess = input("Enter a letter: ").lower()
        while not valid(guess):
            guess = input("Invalid input. Enter a single letter: ").lower()

        if guess in guessed_letters:
            print("You already guessed that letter. Try again.")
        elif guess in word_letters:
            word_letters.remove(guess)
            guessed_letters.add(guess)
            print("Correct guess!")
        else:
            lives -= 1
            guessed_letters.add(guess)
            print("Wrong guess. You lost a life.")

    if lives == 0:
        displayHangman(lives)
        print("Hidden word was:", word)
        return False
    else:
        print("Hidden word was:", word)
        return True

def hangman():
    while True:
        displayIntro()
        result = play()
        displayEnd(result)

        play_again = input("Do you want to play again? (yes/no): ").lower()
        while play_again not in ["yes", "no"]:
            play_again = input("Invalid input. Do you want to play again? (yes/no): ").lower()

        if play_again == "no":
            break

if __name__ == "__main__":
    hangman()