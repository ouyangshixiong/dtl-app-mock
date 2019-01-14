/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { DtlAppMockTestModule } from '../../../test.module';
import { RealnameInfoDetailComponent } from 'app/entities/realname-info/realname-info-detail.component';
import { RealnameInfo } from 'app/shared/model/realname-info.model';

describe('Component Tests', () => {
    describe('RealnameInfo Management Detail Component', () => {
        let comp: RealnameInfoDetailComponent;
        let fixture: ComponentFixture<RealnameInfoDetailComponent>;
        const route = ({ data: of({ realnameInfo: new RealnameInfo(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [DtlAppMockTestModule],
                declarations: [RealnameInfoDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(RealnameInfoDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(RealnameInfoDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.realnameInfo).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
