from random import randint # Do not delete this line

def displayIntro():
    """Displays the welcome screen and game rules."""
    print("_______________________________________________")
    print(" _                                             ")
    print("| |                                            ")
    print("| |__   __ _ _ __   __ _ _ __ ___   __ _ _ __  ")
    print("| '_ \ / _` | '_ \ / _` | '_ ` _ \ / _` | '_ \ ")
    print("| | | | (_| | | | | (_| | | | | | | (_| | | | |")
    print("|_| |_|\__,_|_| |_|\__, |_| |_| |_|\__,_|_| |_|")
    print("                    __/ |                      ")
    print("                   |___/                       ")
    print("_______________________________________________")
    print("_____________________Rules_____________________")
    print("Try to guess the hidden word one letter at a")
    print("time. The number of dashes are equivalent to")
    print("the number of letters in the word. If a player")
    print("suggests a letter that occurs in the word,")
    print("blank places containing this character will be")
    print("filled with that letter. If the word does not")
    print("contain the suggested letter, one new element")
    print("of a hangman\u2019s gallow is painted. As the game")
    print("progresses, a segment of a victim is added for")
    print("every suggested letter not in the word. Goal is")
    print("to guess the word before the man hangs!")
    print("_______________________________________________")

def displayEnd(result):
    """Displays the end game message based on the result (win/lose)."""
    if result:
        print("\nHidden word was: " + hidden_word)
        print(
            """________________________________________________________________________
              _                                  _                          
             (_)                                (_)                         
    __      ___ _ __  _ __   ___ _ __  __      ___ _ __  _ __   ___ _ __    
    \ \ /\ / / | '_ \| '_ \ / _ \ '__| \ \ /\ / / | '_ \| '_ \ / _ \ '__|   
     \ V  V /| | | | | | | |  __/ |     \ V  V /| | | | | | | |  __/ |      
      \_/\_/ |_|_| |_|_| |_|\___|_|      \_/\_/ |_|_| |_|_| |_|\___|_|      
               | |   (_)    | |                  | (_)                      
            ___| |__  _  ___| | _____ _ __     __| |_ _ __  _ __   ___ _ __ 
           / __| '_ \| |/ __| |/ / _ \ '_ \   / _` | | '_ \| '_ \ / _ \ '__|
          | (__| | | | | (__|   <  __/ | | | | (_| | | | | | | | |  __/ |   
           \___|_| |_|_|\___|_|\_\___|_| |_|  \__,_|_|_| |_|_| |_|\___|_|   
    ________________________________________________________________________"""
        )
    else:
        print("\nHidden word was: " + hidden_word)
        print(
            """\
    ._______.
    |/      |
    |      (_)
    |      \|/
    |       |
    |      / \\
    |
    ____|___
    __     __           _           _   _                                    
     \ \   / /          | |         | | | |                                   
      \ \_/ /__  _   _  | | ___  ___| |_| |                                   
       \   / _ \| | | | | |/ _ \/ __| __| |                                   
        | | (_) | |_| | | | (_) \__ \ |_|_|                                   
        |_|\___/ \__,_| |_|\___/|___/\__(_)                                   
            _______ _                                        _ _          _ _ 
           |__   __| |                                      | (_)        | | |
              | |  | |__   ___   _ __ ___   __ _ _ __     __| |_  ___  __| | |
              | |  | '_ \ / _ \ | '_ ` _ \ / _` | '_ \   / _` | |/ _ \/ _` | |
              | |  | | | |  __/ | | | | | | (_| | | | | | (_| | |  __/ (_| |_|
              |_|  |_| |_|\___| |_| |_| |_|\__,_|_| |_|  \__,_|_|\___|\__,_(_)
    __________________________________________________________________________"""
            )

def displayHangman(state):
    hangman_art = [
            r"""
    ._______.
    |/      
    |      
    |      
    |      
    |      
    |      
    ____|___
    """,
            r"""
    ._______.
    |/      |
    |      
    |      
    |      
    |      
    |      
    ____|___
    """,
            r"""
    ._______.
    |/      |
    |      (_)
    |      
    |      
    |      
    |      
    ____|___
    """,
            r"""
    ._______.
    |/      |
    |      (_)
    |       |
    |       |
    |      
    |      
    ____|___
    """,
            r"""
    ._______.
    |/      |
    |      (_)
    |      \|/
    |       |
    |      
    |      
    ____|___
    """,
            r"""
    ._______.
    |/      |
    |      (_)
    |      \|/
    |       |
    |      / 
    |      
    ____|___
    """,
            r"""
    ._______.
    |/      |
    |      (_)
    |      \|/
    |       |
    |      / \
    |      
    ____|___
    """
    ]

    print(hangman_art[5 - state])  # Index into the list based on lives left (0-5)


def getWord():
    """Chooses a random word from the 'hangman-words.txt' file."""
    with open("hangman-words.txt", "r") as file:
        words = file.readlines()
    return words[randint(0, len(words) - 1)].strip()  # Strip newline character

def valid(c):
    """Checks if the input character is a valid lowercase English letter."""
    return len(c) == 1 and c.isalpha() and c.islower()

def play():
    """Implements the main gameplay logic."""
    global hidden_word  # Declare hidden_word as global to modify it
    hidden_word = getWord()
    print(hidden_word)

    lives = 5
    guessed_letters = []
    word_progress = ["_" for _ in hidden_word]

    while lives > 0:
        displayHangman(lives)
        print("Guess the word: " + " ".join(word_progress))
        guess = input("Enter the letter: ")

        while not valid(guess):
            print("Invalid input. Please enter a single lowercase English letter.")
            guess = input("Enter the letter: ")

        if guess in guessed_letters:
            print("You already guessed that letter. Try again.")
            continue

        guessed_letters.append(guess)

        if guess in hidden_word:
            for i, letter in enumerate(hidden_word):
                if letter == guess:
                    word_progress[i] = guess
            if "_" not in word_progress:
                return True  # Player wins
        else:
            lives -= 1

    return False  # Player loses

def hangman():
    """Main game loop."""
    while True:
        displayIntro()
        result = play()
        displayEnd(result)
        again = input("Do you want to play again? (yes/no): ").lower()
        while again not in ["yes", "no"]:
            print("Invalid input. Please enter 'yes' or 'no'.")
            again = input("Do you want to play again? (yes/no): ").lower()
        if again == "no":
            break

if __name__ == "__main__":
    hangman()
