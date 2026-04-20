export class Todo {
    constructor(private _id: number, private _title: string, private _completed: boolean, private _userId?: number) { }

    public get id() : number {
        return this._id;
    }

    public set id(v : number) {
        this._id = v;
    }

    public get title() : string {
        return this._title;
    }

    public set title(v : string) {
        this._title = v;
    }

    public get completed() : boolean {
        return this._completed;
    }

    public set completed(v : boolean) {
        this._completed = v;
    }

    public get userId() : number | undefined {
        return this._userId;
    }

    public set userId(v : number) {
        this._userId = v;
    }
}
