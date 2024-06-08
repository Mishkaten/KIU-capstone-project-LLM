from random import randint # Do not delete this line

def displayIntro():
    """Displays the welcome screen and game rules."""
    with open("hangman-ascii.txt", "r") as f:
        intro_text = f.read()
        print(intro_text.split("yes")[0])  # Print only the intro part

def displayEnd(result):
    """Displays the end game message based on the result."""
    if result:
        print("________________________________________________________________________")
        print("          _                                  _                          ")
        print("         (_)                                (_)                         ")
        print("__      ___ _ __  _ __   ___ _ __  __      ___ _ __  _ __   ___ _ __    ")
        print("\\ \\ /\\ / / | '_ \\| '_ \\ / _ \\ '__| \\ \\ /\\ / / | '_ \\| '_ \\ / _ \\ '__|   ")
        print(" \\ V  V /| | | | | | | |  __/ |     \\ V  V /| | | | | | | |  __/ |      ")
        print("  \\_/\\_/ |_|_| |_|_| |_\\___|_|      \\_/\\_/ |_|_| |_|_| |_\\___|_|      ")
        print("           | |   (_)    | |                  | (_)                      ")
        print("        ___| |__  _  ___| | _____ _ __     __| |_ _ __  _ __   ___ _ __ ")
        print("       / __| '_ \\| |/ __| |/ / _ \\ '_ \\   / _` | | '_ \\| '_ \\ / _ \\ '__|")
        print("      | (__| | | | | (__|   <  __/ | | | | (_| | | | | | | | |  __/ |   ")
        print("       \\___|_| |_|_|\\___|_|\\_\\___|_| |_|  \\__,_|_|_| |_|_| |_\\___|_|   ")
        print("________________________________________________________________________")
    else:
        print("\nYou lost the game!")

def displayHangman(lives):
    """Displays the hangman figure based on the number of lives left."""
    hangman_art = [
        """
     ._______.
     |/
     |
     |
     |
     |
     |
 ____|___
    """,
        """
     ._______.
     |/      |
     |
     |
     |
     |
     |
 ____|___
    """,
        """
     ._______.
     |/      |
     |      (_)
     |
     |
     |
     |
 ____|___
    """,
        """
     ._______.
     |/      |
     |      (_)
     |       |
     |       |
     |
     |
 ____|___
    """,
        """
     ._______.
     |/      |
     |      (_)
     |      \|/
     |       |
     |
     |
 ____|___
    """,
        """
     ._______.
     |/      |
     |      (_)
     |      \|/
     |       |
     |      / \\
     |
 ____|___
    """
    ]
    print(hangman_art[5 - lives])

def getWord():
    """Gets a random word from the 'hangman-words.txt' file."""
    with open("hangman-words.txt", "r") as f:
        words = f.read().splitlines()
    return words[randint(0, len(words) - 1)]

def valid(c):
    """Checks if the input character is a valid lowercase English letter."""
    return len(c) == 1 and c.isalpha() and c.islower()

def play():
    """Plays a single round of Hangman."""
    word_to_guess = getWord()
    print(word_to_guess)
    word_status = ["_" for _ in word_to_guess]
    lives = 5
    guessed_letters = set()

    while lives > 0:
        displayHangman(lives)
        print("Guess the word:", "".join(word_status))

        guess = input("Enter the letter: ").lower()
        while not valid(guess):
            print("Invalid input. Please enter a single lowercase letter.")
            guess = input("Enter the letter: ").lower()

        if guess in guessed_letters:
            print("You already guessed that letter. Try again.")
            continue

        guessed_letters.add(guess)

        if guess in word_to_guess:
            for i, c in enumerate(word_to_guess):
                if c == guess:
                    word_status[i] = c
            if "_" not in word_status:
                displayHangman(lives)
                print("Guess the word:", word_to_guess)
                print("You won!")
                return True
        else:
            lives -= 1
            if lives == 0:
                displayHangman(lives)
                print("You lost! The word was:", word_to_guess)
                return False

def hangman():
    """Main game loop."""
    while True:
        displayIntro()
        result = play()
        displayEnd(result)
        play_again = input("Do you want to play again? (yes/no): ").lower()
        while play_again not in ["yes", "no"]:
            print("Invalid input. Please enter 'yes' or 'no'.")
            play_again = input("Do you want to play again? (yes/no): ").lower()
        if play_again == "no":
            break

if __name__ == "__main__":
    hangman()
