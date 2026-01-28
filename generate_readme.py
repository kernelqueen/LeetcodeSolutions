import os
import re

ROOT = "."
RANGES = ["0001-1000", "1001-2000", "2001-3000", "3001-4000"]

def parse_filename(filename):
    """Extract problem number and title from filename like 123_two_sum.java"""
    match = re.match(r"(\d+)_(.*)\.java", filename)
    if match:
        number = int(match.group(1))
        title = match.group(2).replace("_", " ").title()
        return number, title
    return None, None

def load_existing_readme():
    """Load existing README.md to preserve manual video links."""
    existing = {}
    if os.path.exists("README.md"):
        with open("README.md", "r", encoding="utf-8") as f:
            for line in f:
                # Match a row in the table: | num | title | solution | video |
                parts = [p.strip() for p in line.strip().split("|")]
                if len(parts) >= 5 and parts[1].isdigit():
                    num = int(parts[1])
                    video_col = parts[4] if parts[4] else "-"
                    existing[num] = video_col
    return existing

def generate_table(folder, existing_links):
    """Generate markdown table for one folder"""
    path = os.path.join(ROOT, folder)
    if not os.path.exists(path):
        return f"*(No problems added yet in {folder})*"

    rows = ["| # | Title | Solution | Video |",
            "|---|-------|----------|-------|"]

    for file in sorted(os.listdir(path)):
        if file.endswith(".java"):
            num, title = parse_filename(file)
            if num:
                file_path = f"{folder}/{file}"
                video_col = existing_links.get(num, "-")  # keep old or leave empty
                rows.append(
                    f"| {num} | {title} | [Java]({file_path}) | {video_col} |"
                )

    return "\n".join(rows)

def main():
    existing_links = load_existing_readme()

    readme_lines = [
        "# LeetCode Solutions (Number-wise)\n",
        "This repository contains my solutions to LeetCode problems, organized **by problem number**.\n",
        "---\n",
        "# Watch Explanations\n",
        "Checkout the full walkthrough and solution discussion in Hindi on my [YouTube Channel](https://www.youtube.com/@kernel-queen).\n",
        "---\n",
        "## 📂 Quick Navigation",
    ]

    for folder in RANGES:
        readme_lines.append(f"- [{folder}](#{folder})")
    readme_lines.append("\n---\n")

    for folder in RANGES:
        readme_lines.append(f"## {folder}\n")
        readme_lines.append(generate_table(folder, existing_links))
        readme_lines.append(f"\n\n[🔼 Back to Top](#leetcode-solutions-number-wise)\n")
        readme_lines.append("---\n")

    with open("README.md", "w", encoding="utf-8") as f:
        f.write("\n".join(readme_lines))

    print("✅ README.md updated successfully! Existing video links preserved.")

if __name__ == "__main__":
    main()
