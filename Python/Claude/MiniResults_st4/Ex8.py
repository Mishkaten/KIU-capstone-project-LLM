import pygame

# Initialize Pygame
pygame.init()

# Set the window dimensions
WINDOW_WIDTH = 800
WINDOW_HEIGHT = 600

# Set the square dimensions and initial position
SQUARE_SIZE = 50
square_x = WINDOW_WIDTH // 2 - SQUARE_SIZE // 2
square_y = WINDOW_HEIGHT // 2 - SQUARE_SIZE // 2

# Set the colors
BLACK = (0, 0, 0)
WHITE = (255, 255, 255)

# Create the window
window = pygame.display.set_mode((WINDOW_WIDTH, WINDOW_HEIGHT))
pygame.display.set_caption("Square Movement")

# Game loop
running = True
while running:
    # Handle events
    for event in pygame.event.get():
        if event.type == pygame.QUIT:
            running = False

    # Get the pressed keys
    keys = pygame.key.get_pressed()

    # Move the square based on the pressed keys
    if keys[pygame.K_a]:
        square_x -= 5
    if keys[pygame.K_d]:
        square_x += 5
    if keys[pygame.K_w]:
        square_y -= 5
    if keys[pygame.K_s]:
        square_y += 5

    # Clear the window
    window.fill(BLACK)

    # Draw the square
    pygame.draw.rect(window, WHITE, (square_x, square_y, SQUARE_SIZE, SQUARE_SIZE))

    # Update the display
    pygame.display.update()

# Quit Pygame
pygame.quit()