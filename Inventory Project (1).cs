using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
namespace Main
{
    class inventory
    {
        public string itemName;
        public string itemDesc;
        public string quantity;
        public string unitCost;
        public string retailPrice;
        public void setitemName(string name)
        {
            itemName = name;
        }
        public string getitemName()
        {
            return itemName;
        }
        public void setitemDesc(string desc)
        {
            itemDesc = desc;
        }
        public string getitemDesc()
        {
            return itemDesc;
        }
        public void setquantity(string quant)
        {
            quantity = quant;
        }
        public string getquantity()
        {
            return quantity;
        }
        public void setunitcost(string cost)
        {
            unitCost = cost;
        }
        public string getunitcost()
        {
            return unitCost;
        }
        public void setretailPrice(string retail)
        {
            retailPrice = retail;
        }
        public string getretailPrice()
        {
            return retailPrice;
        }       
    }
}