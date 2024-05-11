import random

def get_word():
    with open("hangman-words.txt", "r") as file:
        words = file.readlines()
    return random.choice(words).strip()

def display_intro():
    print("""
     _                                             
    | |                                            
    | |__   __ _ _ __   __ _ _ __ ___   __ _ _ __  
    | '_ \ / _` | '_ \ / _` | '_ ` _ \ / _` | '_ \ 
    | | | | (_| | | | | (_| | | | | | | (_| | | | |
    |_| |_|\__,_|_| |_|\__, |_| |_| |_|\__,_|_| |_|
                        __/ |                      
                       |___/                       
    _______________________________________________
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
    """)

def display_end(win, word):
    if win:
        print("Congratulations! You guessed the word:", word)
    else:
        print("Game Over! The correct word was:", word)

def display_hangman(lives):
    stages = [
        """
         ___________   
         |/      |   
         |      (_)  
         |      \|/  
         |       |   
         |      / \  
         |           
         |___        
        """,
        """
         ___________   
         |/      |   
         |      (_)  
         |      \|/  
         |       |   
         |      /    
         |           
         |___        
        """,
        """
         ___________   
         |/      |   
         |      (_)  
         |      \|/  
         |       |   
         |           
         |           
         |___        
        """,
        """
         ___________   
         |/      |   
         |      (_)  
         |      \|   
         |       |   
         |           
         |           
         |___        
        """,
        """
         ___________   
         |/      |   
         |      (_)  
         |       |   
         |       |   
         |           
         |           
         |___        
        """,
        """
         ___________   
         |/      |   
         |      (_)  
         |           
         |           
         |           
         |           
         |___        
        """,
        """
         ___________   
         |/      |   
         |           
         |           
         |           
         |           
         |           
         |___        
        """
    ]
    print(stages[lives])

def valid(input_char):
    return input_char.isalpha() and len(input_char) == 1

def play():
    word = get_word()
    guessed = ['_' for _ in word]
    guessed_correctly = set()
    guessed_letters = set()
    lives = 6
    while lives > 0 and '_' in guessed:
        print('\nCurrent word:', ' '.join(guessed))
        display_hangman(lives)
        guess = input("Enter your guess: ").lower()
        
        if not valid(guess):
            print("Invalid input. Please enter a single English letter.")
            continue
        
        if guess in guessed_letters:
            print("You already guessed that letter. Try another one.")
            continue

        guessed_letters.add(guess)
        
        if guess in word:
            for i, letter in enumerate(word):
                if letter == guess:
                    guessed[i] = guess
        else:
            lives -= 1

    display_hangman(lives)
    return lives > 0, word

def hangman():
    while True:
        display_intro()
        win, word = play()
        display_end(win, word)
        if input("Do you want to play again? (yes/no): ").lower() != 'yes':
            break

if __name__ == "__main__":
    hangman()