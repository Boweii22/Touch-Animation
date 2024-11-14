# SandEffectView
![466_1x_shots_so](https://github.com/user-attachments/assets/9bce46d9-376a-4739-9488-4adba9a37861)


`SandEffectView` is a custom Android `View` that creates a dynamic sand particle effect, where particles spawn at the touch point and move with random velocities while gradually fading out. It's a fun and visually appealing effect that can be used for various interactive animations in Android applications.

## Features

- **Touch Interaction**: Spawn sand particles wherever the user touches the screen.
- **Particle Movement**: Particles move in random directions with random speeds.
- **Fading Effect**: Particles gradually fade out as they move.
- **Clear Effect**: Particles are automatically cleared after a short delay (configurable).
- **Customization**: You can adjust the particle size, speed, and clear delay.

## Installation

To integrate `SandEffectView` into your Android project, follow these steps:

1. **Clone or Copy the Code**: Copy the entire `SandEffectView` class and paste it into your project.
2. **Add the View to Your Layout**: You can now add the `SandEffectView` to your layout XML file.

### Example:

```xml
<com.example.animates.SandEffectView
    android:id="@+id/sandEffectView"
    android:layout_width="match_parent"
    android:layout_height="match_parent"/>
```

## Usage

You can interact with the `SandEffectView` by touching or moving your finger across the screen. The particles will appear and move dynamically based on where you touch. The particles will fade out and disappear after 1 second by default, but you can customize this behavior.

### Example Code:

```java
// Access the SandEffectView in your activity or fragment
SandEffectView sandEffectView = findViewById(R.id.sandEffectView);

// Optionally, customize the delay before particles are cleared (in milliseconds)
sandEffectView.setClearDelay(1500);  // Clear particles after 1.5 seconds
```

## Customization

### Particle Color

The default particle color is a sand-like color (#D4B775). You can change this by modifying the `paint.setColor()` line in the `SandEffectView` class:

```java
paint.setColor(Color.parseColor("#D4B775")); // Change this to any color
```

### Particle Count

By default, 10 particles spawn with each touch. You can adjust this by changing the loop count in the `onTouchEvent` method:

```java
for (int i = 0; i < 10; i++) { // Spawn 10 particles per touch
    // Particle properties
}
```

### Clear Delay

The default particle clearing delay is set to 1 second. You can modify this in the `SandEffectView` class or change it programmatically using the `setClearDelay()` method:

```java
sandEffectView.setClearDelay(2000); // Clear after 2 seconds
```

## How It Works

1. **Particles**: Each particle is represented by a `Particle` object with properties like position (`x`, `y`), velocity (`vx`, `vy`), size (`radius`), and opacity (`alpha`).
2. **Touch Events**: When the user touches or moves their finger across the screen, particles are created at the touch position and added to the list of active particles.
3. **Movement & Fading**: During each frame, the particles move according to their velocity, and their opacity decreases until they fade out completely and are removed from the list.
4. **Automatic Clearing**: After a specified delay (default 1 second), the particles are cleared to ensure the view doesn't become cluttered.

## Contributing

Feel free to fork this repository, submit issues, and contribute improvements or bug fixes.
