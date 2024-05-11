import pygame
import sys

# Initialize Pygame
pygame.init()

# Constants
SCREEN_WIDTH, SCREEN_HEIGHT = 640, 480
SQUARE_SIZE = 50
WHITE = (255, 255, 255)
BLUE = (0, 0, 255)

# Screen setup
screen = pygame.display.set_mode((SCREEN_WIDTH, SCREEN_HEIGHT))
pygame.display.set_caption("Move the Square")

# Square initial position (center of the screen)
square_x = SCREEN_WIDTH // 2 - SQUARE_SIZE // 2
square_y = SCREEN_HEIGHT // 2 - SQUARE_SIZE // 2

# Main game loop
running = True
while running:
    for event in pygame.event.get():
        if event.type == pygame.QUIT:
            running = False

    # Key presses
    keys = pygame.key.get_pressed()
    if keys[pygame.K_a]:
        square_x -= 5
    if keys[pygame.K_d]:
        square_x += 5
    if keys[pygame.K_w]:
        square_y -= 5
    if keys[pygame.K_s]:
        square_y += 5

    # Redraw the background and the square
    screen.fill(WHITE)
    pygame.draw.rect(screen, BLUE, (square_x, square_y, SQUARE_SIZE, SQUARE_SIZE))

    # Update the display
    pygame.display.flip()

    # Cap the frame rate
    pygame.time.Clock().tick(60)

# Quit Pygame
pygame.quit()
sys.exit()
