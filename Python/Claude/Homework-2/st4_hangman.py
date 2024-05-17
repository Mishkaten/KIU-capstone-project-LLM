import random


def displayIntro():
    print("""
    _______________________________________________
    _
    | |
    | |__   __ _ _ __   __ _ _ __ ___   __ _ _ __  
    | '_ \ / _` | '_ \ / _` | '_ ` _ \ / _` | '_ \ 
    | | | | (_| | | | | (_| | | | | | | (_| | | | |
    |_| |_|\__,_|_| |_|\__, |_| |_| |_|\__,_|_| |_|
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
         _     _             _     _             _     _             _     _    
        (_)   (_)           (_)   (_)           (_)   (_)           (_)   (_)   
         _     _  __      ___  _____  _   _      _     _  __      ___  _____  _ 
        | |   | | \ \ /\ / / |/ / _ \| | | |    | |   | | \ \ /\ / / |/ / _ \| |
        | |   | |  \ V  V /|   < (_) | |_| |    | |   | |  \ V  V /|   < (_) |_|
        |_|   |_|   \_/\_/ |_|\_\___/ \__, |    |_|   |_|   \_/\_/ |_|\_\___/(_)
                                       __/ |                                    
                                      |___/                                     
        ________________________________________________________________________
        """)
    else:
        print("""
        __   __            _           _   _   _            _                  _ 
        \ \ / /           | |         | | | | | |          | |                | |
         \ V /___   _   _| | ___  ___| |_| |_| |__   ___  | |__   __ _ _ __  | |
          \ // _ \ | | | | |/ _ \/ __| __| __| '_ \ / _ \ | '_ \ / _` | '_ \ | |
          | | (_) | |_| | | (_) \__ \ |_| |_| | | |  __/ | | | | (_| | | | |_|
          |_|\___/ \__,_|_|\___/|___/\__|\__|_| |_|\___| |_| |_|\__,_|_| |_(_)


        """)


def displayHangman(lives):
    stages = [
        """
           ________.
           |/      |
           |      (_)
           |      \|/
           |       |
           |      / \\
           |
        ___|___
        """,
        """
           ________.
           |/      |
           |      (_)
           |      \|/
           |       |
           |      / 
           |
        ___|___
        """,
        """
           ________.
           |/      |
           |      (_)
           |      \|/
           |       |
           |
           |
        ___|___
        """,
        """
           ________.
           |/      |
           |      (_)
           |      \|
           |       |
           |
           |
        ___|___
        """,
        """
           ________.
           |/      |
           |      (_)
           |       |
           |       |
           |
           |
        ___|___
        """,
        """
           ________.
           |/      |
           |      
           |
           |
           |
           |
        ___|___
        """
    ]
    print(stages[lives])


def getWord():
    with open("hangman-words.txt") as f:
        words = f.read().splitlines()
    return random.choice(words)


def valid(char):
    if len(char) != 1:
        return False
    if not char.isalpha() or not char.islower():
        return False
    return True


def play():
    word = getWord()
    print(word)
    lives = 5
    guessed = set()

    while lives > 0:
        displayHangman(lives)
        print("Guess the word: ", end="")
        for char in word:
            if char in guessed:
                print(char, end="")
            else:
                print("_", end="")
        print()

        guess = input("Enter the letter: ").lower()
        while not valid(guess):
            print("Invalid input. Please enter a single lowercase letter.")
            guess = input("Enter the letter: ").lower()

        if guess in guessed:
            print("You already guessed that letter. Try again.")
            continue

        guessed.add(guess)

        if guess in word:
            print("Correct guess!")
        else:
            print("Wrong guess.")
            lives -= 1

        if set(word) == guessed:
            print("Congratulations! You guessed the word correctly.")
            print(f"Hidden word was: " + word)
            return True

    print("Game over! You ran out of lives.")
    return False


def hangman():
    while True:
        displayIntro()
        result = play()
        displayEnd(result)
        play_again = input("Do you want to play again? (yes/no): ")
        if play_again.lower() != "yes":
            break


if __name__ == "__main__":
    hangman()