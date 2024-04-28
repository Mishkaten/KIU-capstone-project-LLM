from random import randint # Do not delete this line
import random

def display_intro():
    print("""
    _______________________________________________
     _                                             
    | |                                            
    | |__   __ _ _ __   __ _ _ __ ___   __ _ _ __  
    | '_ \\ / _` | '_ \\ / _` | '_ ` _ \\ / _` | '_ \\ 
    | | | | (_| | | | | (_| | | | | | | (_| | | | |
    |_| |_|\\__,_|_| |_|\\__, |_| |_| |_|\\__,_|_| |_|
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
    of a hangman’s gallow is painted. As the game  
    progresses, a segment of a victim is added for 
    every suggested letter not in the word. Goal is
    to guess the word before the man hangs!        
    _______________________________________________
    """)

def display_end(won, word):
    if won:
        print("Congratulations! You guessed the word:", word)
    else:
        print("Game over! The correct word was:", word)

def display_hangman(lives):
    stages = [  # Final state: head, torso, both arms, and both legs
                """
                   --------
                   |      |
                   |      O
                   |     \\|/
                   |      |
                   |     / \\
                   -
                """,
                # Head, torso, both arms, and one leg
                """
                   --------
                   |      |
                   |      O
                   |     \\|/
                   |      |
                   |     / 
                   -
                """,
                # Head, torso, and both arms
                """
                   --------
                   |      |
                   |      O
                   |     \\|/
                   |      |
                   |      
                   -
                """,
                # Head, torso, and one arm
                """
                   --------
                   |      |
                   |      O
                   |     \\|
                   |      |
                   |     
                   -
                """,
                # Head and torso
                """
                   --------
                   |      |
                   |      O
                   |      |
                   |      |
                   |     
                   -
                """,
                # Head
                """
                   --------
                   |      |
                   |      O
                   |    
                   |      
                   |     
                   -
                """,
                # Initial empty state
                """
                   --------
                   |      |
                   |      
                   |    
                   |      
                   |     
                   -
                """
    ]
    print(stages[6 - lives])

def get_word():
    with open('hangman-words.txt', 'r') as file:
        words = [line.strip() for line in file]
    return random.choice(words)

def valid(input_char):
    return input_char.isalpha() and len(input_char) == 1

def play():
    word = get_word()
    print(word)
    word_display = ['_' for _ in word]
    guessed = set()
    lives = 6
    while lives > 0 and '_' in word_display:
        print('\nCurrent word:', ' '.join(word_display))
        display_hangman(lives)
        guess = input("Enter a letter: ").lower()
        if not valid(guess):
            print("Please enter a valid English lowercase letter.")
            continue
        if guess in guessed:
            print("You have already guessed that letter. Try again.")
            continue
        guessed.add(guess)
        if guess in word:
            for i, letter in enumerate(word):
                if letter == guess:
                    word_display[i] = guess
        else:
            lives -= 1
            print(f"Wrong! You have {lives} {'lives' if lives != 1 else 'life'} left.")
    display_hangman(lives)
    return lives > 0, word

def hangman():
    while True:
        display_intro()
        won, word = play()
        display_end(won, word)
        if input("Do you want to play again? (yes/no): ").lower() != 'yes':
            break

if __name__ == "__main__":
    hangman()
