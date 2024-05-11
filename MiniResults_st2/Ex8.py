import pygame
import sys

# Initialize Pygame
pygame.init()

# Set up the display
width, height = 640, 480
screen = pygame.display.set_mode((width, height))
pygame.display.set_caption('Move the Square')

# Define colors
black = (0, 0, 0)
white = (255, 255, 255)

# Set up the square properties
square_size = 50
x = width // 2 - square_size // 2
y = height // 2 - square_size // 2

# Game loop
running = True
while running:
    for event in pygame.event.get():
        if event.type == pygame.QUIT:
            running = False

    # Key handling
    keys = pygame.key.get_pressed()
    if keys[pygame.K_a]:
        x -= 5
    if keys[pygame.K_d]:
        x += 5
    if keys[pygame.K_w]:
        y -= 5
    if keys[pygame.K_s]:
        y += 5

    # Ensure the square stays within window boundaries
    x = max(0, min(width - square_size, x))
    y = max(0, min(height - square_size, y))

    # Drawing
    screen.fill(black)  # Clear the screen
    pygame.draw.rect(screen, white, (x, y, square_size, square_size))  # Draw the square

    # Update the display
    pygame.display.flip()

    # Cap the frame rate
    pygame.time.Clock().tick(60)

# Quit Pygame
pygame.quit()
sys.exit()
