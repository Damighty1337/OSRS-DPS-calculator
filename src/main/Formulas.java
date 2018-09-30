package main;

/**
 * ===== Max hit ===== 
 * 
 * 0.5 + A * (B + 64) / 640
 * 
 * A =
 * 1. STR or RNG level
 * 2. Prayer multiplier
 * 3. FLOOR
 * 4. Add stance bonus
 * 5. Add 8
 * 6. Void bonus
 * 
 * B = STR or RNG STR bonus
 * 
 * Stance bonuses:
 * 		Aggressive: +3
 * 		Controlled: +1
 * 	
 * 		Accurate: +3 (ranged only)
 * 	
 * ===== Accuracy =====
 * 
 * Max roll = A * (B + 64)
 * 
 * A = 
 * 1. For attack roll: ATT or RNG level | For defence roll: DEF level
 * 2. Prayer multiplier
 * 3. FLOOR
 * 4. Add stance bonus
 * 5. Add 8
 * 6. Void bonus
 * 7. FLOOR
 * 
 * B = Attack/defence bonus
 * 
 * Stance bonuses:
 * 		Accurate: +3
 * 		Controlled: +1
 * 	
 * 		Accurate: +3 (ranged only)
 * 
 * Hit chance: (ATT and DEF refer to corresponding rolls)
 *  
 * If max attack roll is higher:
 *  	Accuracy: 1 - (DEF + 2) / (2 * (ATT + 1))
 * else:
 *  	Accuracy: ATT / (2 * (DEF + 1))
 */

public class Formulas
{
	int hitStanceBonus;
	int accuracyStanceBonus;
	
	double maxHitEffectiveLevel;
	
	double hitPrayer;
	double accuracyPrayer;
	
	double ATT_ROLL;
	double DEF_ROLL;
	
	public double maxHit(int level, int equipmentBonus, int strengthPrayer, boolean voidMultiplier, int stance, boolean ranged)
	{
		switch(strengthPrayer + 1)
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
		
		switch(stance + 1) 
		{
		case 1:
			if (ranged) hitStanceBonus = 3;
			else hitStanceBonus = 0;
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
			maxHitEffectiveLevel = 1.1 * (Math.floor(level * hitPrayer) + hitStanceBonus + 8);			
		}
		else
		{
			maxHitEffectiveLevel = Math.floor(level * hitPrayer) + hitStanceBonus + 8;
		}
		
		return Math.floor(0.5 + maxHitEffectiveLevel * (equipmentBonus + 64) / 640);
	}
	
	public double accuracy(int level, int equipmentBonus, int attackPrayer, boolean voidMultiplier, int stance, int enemyDefLevel, int enemyDefBonus)
	{
		switch(attackPrayer + 1)
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
		
		switch(stance + 1)
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
			ATT_ROLL = Math.floor(1.1 * (Math.floor(level * accuracyPrayer) + accuracyStanceBonus + 8)) * (equipmentBonus + 64);
		}
		else
		{			
			ATT_ROLL = Math.floor(1.0 * (Math.floor(level * accuracyPrayer) + accuracyStanceBonus + 8)) * (equipmentBonus + 64);
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
