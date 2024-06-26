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

# Set up the display
screen = pygame.display.set_mode((WIDTH, HEIGHT))
pygame.display.set_caption("Snake Game")

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

    def get_head_position(self):
        return self.positions[0]

    def turn(self, point):
        if (point[0] * -1, point[1] * -1) == self.direction:
            return
        else:
            self.direction = point

    def move(self):
        cur = self.get_head_position()
        x, y = self.direction
        new = ((cur[0] + (x * CELL_SIZE)) % WIDTH, (cur[1] + (y * CELL_SIZE)) % HEIGHT)
        if new in self.positions[2:]:
            self.reset()
        else:
            self.positions.insert(0, new)
            if len(self.positions) > self.length:
                self.positions.pop()

    def reset(self):
        self.length = 5
        self.positions = [(WIDTH // 2, HEIGHT // 2) + (i * CELL_SIZE, 0) for i in range(self.length)]
        self.direction = RIGHT
        self.score = 0

    def draw(self, surface):
        for p in self.positions:
            rect = pygame.Rect((p[0], p[1]), (CELL_SIZE, CELL_SIZE))
            pygame.draw.rect(surface, self.color, rect)
            pygame.draw.rect(surface, BLACK, rect, 1)

    def handle_keys(self):
        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                pygame.quit()
                sys.exit()
            elif event.type == pygame.KEYDOWN:
                if event.key == pygame.K_UP:
                    self.turn(UP)
                elif event.key == pygame.K_DOWN:
                    self.turn(DOWN)
                elif event.key == pygame.K_LEFT:
                    self.turn(LEFT)
                elif event.key == pygame.K_RIGHT:
                    self.turn(RIGHT)

class Food:
    def __init__(self):
        self.position = (0, 0)
        self.color = RED
        self.randomize_position()

    def randomize_position(self):
        self.position = (random.randint(0, (WIDTH - CELL_SIZE) // CELL_SIZE) * CELL_SIZE,
                         random.randint(0, (HEIGHT - CELL_SIZE) // CELL_SIZE) * CELL_SIZE)

    def draw(self, surface):
        rect = pygame.Rect((self.position[0], self.position[1]), (CELL_SIZE, CELL_SIZE))
        pygame.draw.rect(surface, self.color, rect)
        pygame.draw.rect(surface, BLACK, rect, 1)

def drawGrid(surface):
    for y in range(0, int(HEIGHT), CELL_SIZE):
        for x in range(0, int(WIDTH), CELL_SIZE):
            rect = pygame.Rect(x, y, CELL_SIZE, CELL_SIZE)
            pygame.draw.rect(surface, BLACK, rect, 1)

def main():
    clock = pygame.time.Clock()
    snake = Snake()
    food = Food()

    while True:
        snake.handle_keys()
        snake.move()
        if snake.get_head_position() == food.position:
            snake.length += 1
            snake.score += 1
            food.randomize_position()

        screen.fill(WHITE)
        snake.draw(screen)
        food.draw(screen)
        drawGrid(screen)
        pygame.display.update()
        clock.tick(10)

if __name__ == "__main__":
    main()