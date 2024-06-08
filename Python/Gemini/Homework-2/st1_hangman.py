from random import randint

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
    """Displays the end game message based on the result."""
    if result:
        print("\nHidden word was guessed correctly!")
        print("""________________________________________________________________________
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
________________________________________________________________________""")
    else:
        print("\nYou lost! The man is hanged!")
        print(f"The hidden word was: {hidden_word}")
        print(""" __     __           _           _   _                                    
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
__________________________________________________________________________""")

def displayHangman(lives):
    """Displays the hangman figure based on the number of lives left."""
    with open("hangman-ascii.txt", "r") as f:
        lines = f.readlines()
        start = 18 * (5 - lives)  # Calculate the starting line for the current state
        end = start + 18  # Calculate the ending line
        for line in lines[start:end]:
            print(line, end="")

def getWord():
    """Chooses a random word from hangman-words.txt."""
    with open("hangman-words.txt", "r") as f:
        words = f.read().splitlines()
    return words[randint(0, len(words) - 1)]

def valid(c):
    """Checks if the input character is a valid lowercase English letter."""
    return len(c) == 1 and c.isalpha() and c.islower()

def play():
    """Main game logic."""
    global hidden_word  # Use global variable to store the hidden word
    hidden_word = getWord()
    lives = 5
    guessed_letters = []
    word_progress = ["_" for _ in hidden_word]

    while lives > 0:
        displayHangman(lives)
        print("Guess the word:", "".join(word_progress))
        guess = input("Enter the letter: ")

        if not valid(guess):
            print("Invalid input. Please enter a single lowercase letter.")
            continue

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
        play_again = input("Do you want to play again? (yes/no): ").lower()
        if play_again != "yes":
            break

if __name__ == "__main__":
    hangman()
