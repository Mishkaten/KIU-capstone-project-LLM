import random

def displayIntro():
    print("""
_______________________________________________
 _   _
| | | |
| |__   __ _ _ __   __ _ _ __ ___   __ _ _ __
| '_ \ / _` | '_ \ / _` | '_ ` _ \ / _` | '_ \\
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
 _       _                                         _       _
(_)     (_)                                       (_)     (_)
__      ___  _ __   _ __     ___ _   _ ___  ___ ___  ___ ___  ___   _ __
\ \ /\ / / | '_ \ | '_ \   / __| | | / __|/ __/ _ \/ __/ __|/ _ \ | '__|
 \ V  V /| | | | || | | | | (__| |_| \__ \ (_|  __/\__ \__ \  __/_| |
  \_/\_/ |_|_| |_||_| |_|  \___|\__,_|___/\___\___||___/___/\___(_)_|

  ________________________________________________________________________
        """)
    else:
        print("""
__   __
\ \ / /
 \ V /___  _   _    _    ___  ___| |_| |
  > </ _ \| | | |  | |  / _ \/ __| __| |
 / . \ (_) | |_| |  | | | (_) \__ \ |_|_|
/_/ \_\___/ \__,_|  |_|  \___/|___/\__(_)

__   __               _           _   _
\ \ / /              | |         | | | |
 \ V /   ___   _   _ | |   ___  ___ | |_| |
  > <   / _ \ | | | || |  / _ \/ __|| __| |
 / . \ | (_) || |_| || | | (_) \__ \| |_|_|
/_/ \_\ \___/  \__,_||_|  \___/|___/ \__(_)

__________________________________________________________________________
        """)
    print("__________________________________________________________________________")

def displayHangman(lives):
    hangman_stages = [
        """
           ._______.
           |/      |
           |      (_)
           |      \|/
           |       |
           |      / \\
           |
        ___|___
        """,
        """
           ._______.
           |/      |
           |      (_)
           |      \|/
           |       |
           |      /
           |
        ___|___
        """,
        """
           ._______.
           |/      |
           |      (_)
           |      \|/
           |       |
           |
           |
        ___|___
        """,
        """
           ._______.
           |/      |
           |      (_)
           |      \|/
           |
           |
           |
        ___|___
        """,
        """
           ._______.
           |/      |
           |      (_)
           |
           |
           |
           |
        ___|___
        """,
        """
           ._______.
           |/      |
           |
           |
           |
           |
           |
        ___|___
        """
    ]
    print(hangman_stages[lives])

def getWord():
    with open("hangman-words.txt", "r") as file:
        words = file.read().splitlines()
    return random.choice(words)

def valid(guess):
    return len(guess) == 1 and guess.isalpha() and guess.islower()

def play():
    word = getWord()
    print(word)
    word_letters = set(word)
    alphabet = set('abcdefghijklmnopqrstuvwxyz')
    used_letters = set()
    lives = 5

    while len(word_letters) > 0 and lives > 0:
        displayHangman(lives)

        current_word = [letter if letter in used_letters else '_' for letter in word]
        print("Guess the word:", ' '.join(current_word))

        guess = input("Enter the letter:\n> ").lower()

        if not valid(guess):
            print("Invalid input. Please enter a single lowercase letter.")
            continue

        if guess in used_letters:
            print("You have already guessed that letter. Try again.")
            continue

        used_letters.add(guess)

        if guess in word_letters:
            word_letters.remove(guess)
        else:
            lives -= 1
            print("Wrong guess!")

    displayHangman(lives)
    print("Hidden word was:", word)


    if lives == 0:
        return False
    else:
        return True

def hangman():
    while True:
        displayIntro()
        result = play()
        displayEnd(result)

        play_again = input("Do you want to play again? (yes/no)\n> ").lower()
        if play_again != 'yes':
            break

if __name__ == "__main__":
    hangman()