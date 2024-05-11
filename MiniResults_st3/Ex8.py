import pygame
import sys

# Initialize Pygame
pygame.init()

# Constants for the screen dimensions
SCREEN_WIDTH, SCREEN_HEIGHT = 640, 480
# Color definitions
WHITE = (255, 255, 255)
BLUE = (0, 0, 255)

# Set up the display
screen = pygame.display.set_mode((SCREEN_WIDTH, SCREEN_HEIGHT))
pygame.display.set_caption("Move the Square")

# Square properties
square_size = 50
square_x = (SCREEN_WIDTH - square_size) // 2
square_y = (SCREEN_HEIGHT - square_size) // 2
square_velocity = 5

# Clock for controlling the frame rate
clock = pygame.time.Clock()

def draw_square():
    pygame.draw.rect(screen, BLUE, (square_x, square_y, square_size, square_size))

# Main game loop
running = True
while running:
    for event in pygame.event.get():
        if event.type == pygame.QUIT:
            running = False

    # Check for key presses
    keys = pygame.key.get_pressed()
    if keys[pygame.K_a]:
        square_x -= square_velocity
    if keys[pygame.K_d]:
        square_x += square_velocity
    if keys[pygame.K_w]:
        square_y -= square_velocity
    if keys[pygame.K_s]:
        square_y += square_velocity

    # Clear the screen
    screen.fill(WHITE)
    # Draw the square
    draw_square()
    # Update the display
    pygame.display.flip()

    # Limit the frame rate
    clock.tick(60)

# Quit Pygame
pygame.quit()
sys.exit()
