# README.md

This file provides guidance to when working with code in this repository.

## Project Overview

This is a text-based adventure game written in Java where players select a character, navigate locations, engage in combat, and collect survival items to win. The game uses a console-based interface with menu-driven navigation.

## Build and Run Commands

**Compile the project:**
```bash
javac -d out/production/Adventure\ Game/ src/**/*.java
```

**Run the game:**
```bash
java -cp out/production/Adventure\ Game/ Game
```

## Core Architecture

### Package Structure
- `src/Characters/` - Player character types (Archer, Knight, Samurai) with different starting stats
- `src/Enemies/` - Enemy types (Bear, Snake, Vampire, Zombie) with combat and loot mechanics  
- `src/General/` - Core game mechanics (Player, Inventory, Locations)

### Key Class Hierarchies

**Location System:**
```
Location (abstract)
├── NormalLocation (SafeHouse, ToolStore)
└── BattleLocation (Forest, Cave, River, Mine)
```

BattleLocation implements Template Method pattern with abstract methods:
- `combat()` - Main battle loop with 50-50 chance for first move
- `playerAttack()` - Player's turn logic
- `enemyAttack()` - Enemy's turn logic

**Combat System:**
- Each BattleLocation spawns 1-5 random enemies
- Combat alternates turns between player and enemies
- Enemies have `randomDamage()` and `dropLoot()` methods
- Snake enemies specifically have random damage (3-6) and complex loot probability system

### Game Flow
1. Player selects character type (affects starting stats)
2. Main game loop presents location menu
3. Player visits locations to buy equipment or engage in combat
4. Victory requires collecting all survival items: water (River), food (Cave), firewood (Forest)
5. SafeHouse restores health between battles

## Important Implementation Details

### Combat Mechanics
- Player and enemy damage calculated with equipment bonuses
- 50-50 random chance determines who attacks first each battle
- Player death results in immediate game over
- Equipment (weapons/armor) can be found as loot or purchased

### Loot System
- Snake dropLoot() has complex probability distribution:
  - 45% nothing, 25% gold (1/5/10), 15% weapons, 15% armor
  - Within each category, items have different rarities (50%/30%/20% distribution)

### Player State
- Tracks completion status for each battle location
- Maintains inventory with single weapon/armor slots
- Gold system for purchasing equipment

## Code Conventions

- Use proper Java naming conventions
- Battle locations should implement the three abstract combat methods
- Enemy classes extend base Enemy class and can override damage/loot behaviors
- Location completion is tracked via boolean flags in Player class
- System.out.println() used for all game output (console-based interface)