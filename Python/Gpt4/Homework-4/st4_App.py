import pygame
import random
import sys

# Initialize Pygame
pygame.init()

# Constants for the display
WIDTH, HEIGHT = 640, 480
CELL_SIZE = 20
assert WIDTH % CELL_SIZE == 0 and HEIGHT % CELL_SIZE == 0, "Screen dimensions must be a multiple of cell size."

# Colors
BLACK = (0, 0, 0)
WHITE = (255, 255, 255)
GREEN = (0, 255, 0)
RED = (255, 0, 0)
GOLD = (255, 215, 0)

# Set up the display
screen = pygame.display.set_mode((WIDTH, HEIGHT))
pygame.display.set_caption("Snake Game")

# Set up font for displaying the score and game over text
font = pygame.font.Font(None, 36)
game_over_font = pygame.font.Font(None, 72)  # Larger font for Game Over

# Directions
UP = (0, -1)
DOWN = (0, 1)
LEFT = (-1, 0)
RIGHT = (1, 0)

class Snake:
    def __init__(self):
        self.length = 5
        self.positions = [(WIDTH // 2, HEIGHT // 2) + (i * CELL_SIZE, 0) for i in range(self.length)]
        self.direction = RIGHT
        self.color = GREEN
        self.score = 0
        self.alive = True

    def get_head_position(self):
        return self.positions[0]

    def turn(self, point):
        # This prevents the snake from turning directly into itself
        if (point[0] * -1, point[1] * -1) != self.direction:
            self.direction = point

    def move(self):
        if not self.alive:
            return
        cur = self.get_head_position()
        x, y = self.direction
        new = ((cur[0] + (x * CELL_SIZE)) % WIDTH, (cur[1] + (y * CELL_SIZE)) % HEIGHT)
        if new in self.positions[2:] or new[0] < 0 or new[0] >= WIDTH or new[1] < 0 or new[1] >= HEIGHT:
            self.alive = False  # Snake dies if it runs into itself or the boundaries
        else:
            self.positions.insert(0, new)
            if len(self.positions) > self.length:
                self.positions.pop()

    def reset(self):
        self.length = 5
        self.positions = [(WIDTH // 2, HEIGHT // 2) + (i * CELL_SIZE, 0) for i in range(self.length)]
        self.direction = RIGHT
        self.score = 0
        self.alive = True

    def draw(self, surface):
        for p in self.positions:
            rect = pygame.Rect((p[0], p[1]), (CELL_SIZE, CELL_SIZE))
            pygame.draw.rect(surface, self.color, rect)
            pygame.draw.rect(surface, BLACK, rect, 1)

    def handle_keys(self, event):
        if event.key == pygame.K_UP and self.direction != DOWN:
            self.turn(UP)
        elif event.key == pygame.K_DOWN and self.direction != UP:
            self.turn(DOWN)
        elif event.key == pygame.K_LEFT and self.direction != RIGHT:
            self.turn(LEFT)
        elif event.key == pygame.K_RIGHT and self.direction != LEFT:
            self.turn(RIGHT)


class Food:
    def __init__(self):
        self.position = (0, 0)
        self.color = RED
        self.is_golden = False
        self.randomize_position()

    def randomize_position(self):
        self.position = (random.randint(0, (WIDTH - CELL_SIZE) // CELL_SIZE) * CELL_SIZE,
                         random.randint(0, (HEIGHT - CELL_SIZE) // CELL_SIZE) * CELL_SIZE)
        if random.randint(1, 5) == 5:  # Every 5th fruit has a chance to be golden
            self.is_golden = True
            self.color = GOLD
        else:
            self.is_golden = False
            self.color = RED

    def draw(self, surface):
        rect = pygame.Rect((self.position[0], self.position[1]), (CELL_SIZE, CELL_SIZE))
        pygame.draw.rect(surface, self.color, rect)
        pygame.draw.rect(surface, BLACK, rect, 1)

def drawGrid(surface):
    for y in range(0, int(HEIGHT), CELL_SIZE):
        for x in range(0, int(WIDTH), CELL_SIZE):
            rect = pygame.Rect(x, y, CELL_SIZE, CELL_SIZE)
            pygame.draw.rect(surface, BLACK, rect, 1)

def display_score(surface, score):
    score_text = font.render(f"Score: {score}", True, BLACK)
    surface.blit(score_text, (10, 10))

def display_game_over(surface, score):
    game_over_text = game_over_font.render("Game Over", True, RED)
    score_text = game_over_font.render(f"Final Score: {score}", True, RED)
    surface.blit(game_over_text, (WIDTH // 2 - game_over_text.get_width() // 2, HEIGHT // 2 - game_over_text.get_height() // 2))
    surface.blit(score_text, (WIDTH // 2 - score_text.get_width() // 2, HEIGHT // 2 + score_text.get_height() // 2))

def main():
    clock = pygame.time.Clock()
    snake = Snake()
    food = Food()

    running = True
    while running:
        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                running = False
            elif event.type == pygame.KEYDOWN:
                snake.handle_keys(event)

        if not snake.alive:
            display_game_over(screen, snake.score)
            pygame.display.update()
            pygame.time.wait(2000)  # Wait for 2 seconds before closing
            running = False
            continue

        snake.move()
        if snake.get_head_position() == food.position:
            snake.length += 1
            snake.score += 5 if food.is_golden else 1
            food.randomize_position()

        screen.fill(WHITE)
        snake.draw(screen)
        food.draw(screen)
        drawGrid(screen)
        display_score(screen, snake.score)
        pygame.display.update()
        clock.tick(10)

    pygame.quit()
    sys.exit()


if __name__ == "__main__":
    main()
