# Sui Move IntelliJ Plugin

This is a Sui Move IntelliJ plugin powered by `move-analyzer`.

## Requirements

1. JetBrains IDE version: `243+`
2. `move-analyzer` installed

Install `move-analyzer`:

```bash
suiup install move-analyzer
```

Verify:

```bash
move-analyzer --version
```

## Install the Plugin

### Option 1: Install from ZIP (recommended for local testing)

1. Open your IDE.
2. Go to `Settings/Preferences -> Plugins`.
3. Click the gear icon and select `Install Plugin from Disk...`.
4. Select the packaged ZIP file (`build/distributions/*.zip`).
5. Restart the IDE after installation.

### Option 2: Build locally, then install

Run in the project root:

```bash
./gradlew buildPlugin
```

Generated file location:

```text
build/distributions/
```

Then install it using Option 1.

## First-time Configuration

1. Go to `Settings/Preferences -> Languages & Frameworks -> Sui Move Language`.
2. Set the `move-analyzer path` to your executable.
   - Example: `/Users/<you>/.local/bin/move-analyzer`
3. Save settings.

If `move-analyzer` is not found, the plugin will prompt you to install it.

## Troubleshooting

### 1. Path is configured but not working

- Verify the path is executable:

```bash
/your/path/to/move-analyzer --version
```

- After changing the path, reopen the project or restart the IDE.

### 2. No diagnostics shown

- Make sure the current file is a `.move` file.
- Make sure the project is a valid Move/Sui project (`Move.toml` exists).
- Make sure `move-analyzer` is available and runnable.
