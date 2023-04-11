export class AccountingRecord {
    id: string;
    name: string;
    type: string;
    date: any;
    amount: number;

    constructor() {
        this.id = "";
        this.name = "";
        this.type = "";
        this.date = "";
        this.amount = 0;
    }

    toString(): string {
        return `${this.name} ${this.type} ${this.date} ${this.amount}`;
    }
}