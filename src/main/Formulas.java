package main;

public class Formulas
{
	int hitStanceBonus;
	int accuracyStanceBonus;

	double strengthLevel;
	double attackLevel;
	double maxHitEffectiveLevel;
	double hitPrayer;
	double accuracyPrayer;
	double ATT_ROLL;
	double DEF_ROLL;

	public double maxHit(int level, int equipmentBonus, int strengthPotion, int strengthPrayer, boolean voidMultiplier,
			int stance, boolean ranged, boolean dharoks, double currentHealth, double maxHealth)
	{
		strengthLevel = level;

		switch (strengthPotion + 1)
		{
		case 2:
			strengthLevel += 3 + Math.floor(0.1 * level);
			break;
		case 3:
			strengthLevel += 5 + Math.floor(0.15 * level);
			break;
		case 4:
			strengthLevel += 2 + Math.floor(0.12 * level);
			break;
		case 5:
			strengthLevel += 5 + Math.floor(0.15 * level);
			break;
		default:
			strengthLevel = level;
			break;
		}

		switch (strengthPrayer + 1)
		{
		case 1:
			hitPrayer = 1.00;
			break;
		case 2:
			hitPrayer = 1.05;
			break;
		case 3:
			hitPrayer = 1.10;
			break;
		case 4:
			hitPrayer = 1.15;
			break;
		case 5:
			hitPrayer = 1.18;
			break;
		case 6:
			hitPrayer = 1.23;
			break;
		case 7:
			hitPrayer = 1.23;
			break;
		default:
			hitPrayer = 1.00;
			break;
		}

		switch (stance + 1)
		{
		case 1:
			if (ranged)
				hitStanceBonus = 3;
			else
				hitStanceBonus = 0;
			break;
		case 2:
			hitStanceBonus = 3;
			break;
		case 3:
			hitStanceBonus = 1;
			break;
		case 4:
			hitStanceBonus = 0;
			break;
		default:
			hitStanceBonus = 0;
			break;
		}

		if (voidMultiplier)
		{
			maxHitEffectiveLevel = 1.1 * (Math.floor(strengthLevel * hitPrayer) + hitStanceBonus + 8);
		} 
		else
		{
			maxHitEffectiveLevel = Math.floor(strengthLevel * hitPrayer) + hitStanceBonus + 8;
		}

		if (dharoks)
		{
			return Math.floor((1 + (maxHealth - currentHealth) / 100)
					* (Math.floor(0.5 + maxHitEffectiveLevel * (equipmentBonus + 64) / 640)));
		} 
		else
		{
			return Math.floor(0.5 + maxHitEffectiveLevel * (equipmentBonus + 64) / 640);
		}
	}

	public double accuracy(int level, int equipmentBonus, int attackPotion, int attackPrayer, boolean voidMultiplier,
			int stance, int enemyDefLevel, int enemyDefBonus)
	{
		attackLevel = level;

		switch (attackPotion + 1)
		{
		case 2:
			attackLevel += 3 + Math.floor(0.1 * level);
			break;
		case 3:
			attackLevel += 5 + Math.floor(0.15 * level);
			break;
		case 4:
			attackLevel += 2 + Math.floor(0.2 * level);
			break;
		case 5:
			attackLevel += 4 + Math.floor(0.1 * level);
			break;
		case 6:
			attackLevel += 5 + Math.floor(0.15 * level);
			break;
		case 7:
			attackLevel += 5 + Math.floor(0.15 * level);
			break;
		default:
			attackLevel = level;
			break;
		}

		switch (attackPrayer + 1)
		{
		case 1:
			accuracyPrayer = 1.00;
			break;
		case 2:
			accuracyPrayer = 1.05;
			break;
		case 3:
			accuracyPrayer = 1.10;
			break;
		case 4:
			accuracyPrayer = 1.15;
			break;
		case 5:
			accuracyPrayer = 1.15;
			break;
		case 6:
			accuracyPrayer = 1.20;
			break;
		case 7:
			accuracyPrayer = 1.20;
			break;
		default:
			accuracyPrayer = 1.00;
			break;
		}

		switch (stance + 1)
		{
		case 1:
			accuracyStanceBonus = 3;
			break;
		case 2:
			accuracyStanceBonus = 0;
			break;
		case 3:
			accuracyStanceBonus = 1;
			break;
		case 4:
			accuracyStanceBonus = 0;
			break;
		default:
			accuracyStanceBonus = 0;
			break;
		}

		if (voidMultiplier)
		{
			ATT_ROLL = Math.floor(1.1 * (Math.floor(attackLevel * accuracyPrayer) + accuracyStanceBonus + 8))
					* (equipmentBonus + 64);
		}
		else
		{
			ATT_ROLL = Math.floor(1.0 * (Math.floor(attackLevel * accuracyPrayer) + accuracyStanceBonus + 8))
					* (equipmentBonus + 64);
		}

		DEF_ROLL = (enemyDefLevel + 8) * (enemyDefBonus + 64);

		if (ATT_ROLL > DEF_ROLL)
		{
			return 1 - (DEF_ROLL + 2) / (2 * (ATT_ROLL + 1));
		}
		else
		{
			return ATT_ROLL / (2 * (DEF_ROLL + 1));
		}
	}
}
